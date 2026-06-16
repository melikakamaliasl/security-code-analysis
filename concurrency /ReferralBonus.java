package concurrency;

import java.sql.*;

public class ReferralBonus {
    private Connection conn;

    public ReferralBonus(Connection conn) {
        this.conn = conn;
    }

    public void claimBonus(String userId) throws SQLException {
        String checkQuery = "SELECT claimed FROM referral_bonuses WHERE user_id = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setString(1, userId);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next() && !rs.getBoolean("claimed")) {
            String updateQuery = "UPDATE referral_bonuses SET claimed = true " +
                    "WHERE user_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, userId);
            updateStmt.executeUpdate();
            creditAccount(userId, 50.00);
        }
    }

    private void creditAccount(String userId, double amount) {}
}