package com.github.dhslrl321.app;

import com.github.dhslrl321.app.person.Man;
import com.github.dhslrl321.cat.PersianCat;
import com.github.dhslrl321.dog.Maltese;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class ComponentScanning_MyBaseApplicationTest {

    @Autowired
    ConfigurableApplicationContext sut;

    @Test
    @DisplayName("`@SpringBootApplication` 과 동일한 패키지의 bean 은 자동으로 scan 한다")
    void can_scan_same_package_bean() {
        Man samePkgBean = sut.getBean("man", Man.class);

        assertThat(samePkgBean).isNotNull();
    }

    @Test
    @DisplayName("`basePackageClasses` 속성을 통해서 bean scan 대상 패키지를 추가할 수 있다")
    void can_scan_basePackageClasses_props() {
        Maltese actual = sut.getBean("maltese", Maltese.class);

        assertThat(actual).isNotNull();
    }

    @Test
    @DisplayName("`@SpringBootApplication` 가 명시된 클래스의 하위 패키지에 존재하지 않고 따로 명시하지 않았다면 scan 할 수 없다")
    void cannot_scan_outside_of_root() {
        assertThatThrownBy(() -> sut.getBean("persianCat", PersianCat.class))
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}