package concurrency;

import java.sql.*;

public class SecureReferralBonus {
    private Connection conn;

    public SecureReferralBonus(Connection conn) {
        this.conn = conn;
    }

    public void claimBonus(String userId) throws SQLException {
        String query = "UPDATE referral_bonuses SET claimed = true " +
                "WHERE user_id = ? AND claimed = false";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, userId);
        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated > 0) {
            creditAccount(userId, 50.00);
        }
    }

    private void creditAccount(String userId, double amount) {}
}