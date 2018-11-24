package com.anna.coupons.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anna.coupons.beans.*;
import com.anna.coupons.enums.ErrorType;
import com.anna.coupons.exceptions.ApplicationException;
import com.anna.coupons.utils.JdbcUtils;

public class CompanyDao {

	public void createCompany(Company company) throws ApplicationException {

		java.sql.PreparedStatement preparedStatement = null;
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnection();

			String sql = "insert into company (companyName, companyPassword, companyEmail) values (?,?,?)";

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyPassword());
			preparedStatement.setString(3, company.getCompanyEmail());

			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, createComapny(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}

	}

	public Company getCompany(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM company WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			company = extractCompanyFromResultSet(resultSet);

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR,
					"Error in couponDao, getCompany(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return company;
	}

	private Company extractCompanyFromResultSet(ResultSet resultSet) throws SQLException {
		Company company = new Company();
		company.setCompanyId(resultSet.getLong("companyId"));
		company.setCompanyName(resultSet.getString("companyName"));
		company.setCompanyPassword(resultSet.getString("companyPassword"));
		company.setCompanyEmail(resultSet.getString("companyEmail"));

		return company;
	}

	public void deleteCompany(long companyId) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CouponDao couponDao = new CouponDao();

		try {
			couponDao.deleteCouponByCompany(companyId);
			connection = JdbcUtils.getConnection();
			String sql = "DELETE FROM company WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, companyId);
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in companyDao, deleteCompany(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

	}

	public void updateCompany(Company company) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "UPDATE company SET companyName = ?, companyPassword = ?, companyEmail = ? WHERE companyId = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, company.getCompanyName());
			preparedStatement.setString(2, company.getCompanyPassword());
			preparedStatement.setString(3, company.getCompanyEmail());
			preparedStatement.setLong(4, company.getCompanyId());
			preparedStatement.executeUpdate();
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, updateCoupon(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
	}

	public List<Company> getAllComapnies() throws ApplicationException {

		List<Company> companyList = new ArrayList<>();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Company company = null;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT * FROM company";
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				company = extractCompanyFromResultSet(resultSet);
				companyList.add(company);
			}

		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in couponDao, getAllCompanies(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}

		return companyList;
	}

	public Long login(String companyEmail, String companyPassword) throws ApplicationException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		long companyId = 0;

		try {
			connection = JdbcUtils.getConnection();
			String sql = "SELECT companyId FROM company WHERE comapnyEmail = ? AND comapnyPassword = ? ";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, companyEmail);
			preparedStatement.setString(2, companyPassword);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {

				companyId = resultSet.getLong("customerId");
			} else
				return null;
		}

		catch (SQLException e) {
			throw new ApplicationException(e, ErrorType.SYSTEM_ERROR, "Error in companyDao, login(); Failed");
		}

		finally {
			JdbcUtils.closeResources(connection, preparedStatement, resultSet);
		}
		return companyId;
	}

}
