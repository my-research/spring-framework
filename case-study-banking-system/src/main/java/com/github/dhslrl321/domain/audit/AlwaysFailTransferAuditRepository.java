package com.github.dhslrl321.domain.audit;

import com.github.dhslrl321.supports.ConnectionSupports;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlwaysFailTransferAuditRepository implements TransferAuditRepository {
    public static final String TABLE_NAME = "transfer_audits";
    private final ConnectionSupports supports;

    public AlwaysFailTransferAuditRepository(DataSource dataSource) {
        supports = new ConnectionSupports(dataSource);
    }

    @Override
    public List<TransferAudit> findAll() {
        Connection conn = supports.connection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {

            statement = conn.prepareStatement("SELECT * FROM transfer_audits");

            rs = statement.executeQuery();
            List<TransferAudit> audits = new ArrayList<>();
            while (rs.next()) {
                audits.add(TransferAudit.instantiate(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getLong(3),
                        rs.getLong(4),
                        rs.getTimestamp(5).toLocalDateTime()));
            }

            return audits;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            supports.release(conn, statement, rs);
        }
    }

    @Override
    public TransferAudit findBy(long id) {
        Connection conn = supports.connection();
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            if (!supports.isExist(TABLE_NAME, id)) {
                throw new IllegalArgumentException("존재하지 않는 audit");
            }
            statement = conn.prepareStatement("SELECT * FROM transfer_audits WHERE id = ?");
            statement.setLong(1, id);

            rs = supports.executeQuery(statement);
            TransferAudit found = TransferAudit.instantiate(
                    rs.getLong(1),
                    rs.getLong(2),
                    rs.getLong(3),
                    rs.getLong(4),
                    rs.getTimestamp(5).toLocalDateTime());

            return found;
        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            supports.release(conn, statement, rs);
        }
    }

    @Override
    public void save(TransferAudit transferAudit) {
        throw new RuntimeException();
    }
}
