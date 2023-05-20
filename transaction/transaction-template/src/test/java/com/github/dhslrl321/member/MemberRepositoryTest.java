package com.github.dhslrl321.member;

import com.github.dhslrl321.DataSourceConfigs;
import com.github.dhslrl321.member.Member;
import com.github.dhslrl321.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberRepositoryTest {

    MemberRepository sut;

    @BeforeEach
    void setUp() {
        DataSource dataSource = DataSourceConfigs.hikariConnectionPoolDataSource4Test();
        sut = new MemberRepository(dataSource);
    }

    @Test
    @DisplayName("pk 제약조건")
    void name() {
        Member member = Member.instantiate(1, "jang", 100);

        sut.save(member);

        assertThatThrownBy(() -> sut.save(member))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 존재하는 유저");
    }

    @Test
    @DisplayName("조회")
    void name2() {
        Member member = Member.instantiate(1, "jang", 100);

        sut.save(member);
        Member actual = sut.findBy(1);

        assertThat(actual.getId()).isEqualTo(1);
    }

    @Test
    @DisplayName("존재하지 않는 대상을 조회")
    void name3() {
        assertThatThrownBy(() -> sut.findBy(9999))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("존재하지 않는 유저");
    }
}