/*
 * This file is part of HeavySpleef.
 * Copyright (c) 2014-2016 Matthias Werning
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.xaniox.heavyspleef.persistence.sql;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class ForwardingCompatConnection implements Connection {

	private SQLDatabaseContext.SQLImplementation implementation;
	private Connection delegate;

	public static ForwardingCompatConnection wrap(Connection delegate, SQLDatabaseContext.SQLImplementation implementation) {
		return new ForwardingCompatConnection(delegate, implementation);
	}
	
	private ForwardingCompatConnection(Connection delegate, SQLDatabaseContext.SQLImplementation implementation) {
		this.delegate = delegate;
		this.implementation = implementation;
	}
	
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return delegate.unwrap(iface);
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return delegate.isWrapperFor(iface);
	}
	
	@Override
	public Statement createStatement() throws SQLException {
		return delegate.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getResultSetHoldability());
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return delegate.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, getResultSetHoldability());
	}

	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		return delegate.prepareCall(sql);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return delegate.nativeSQL(sql);
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		delegate.setAutoCommit(autoCommit);
	}

	@Override
	public boolean getAutoCommit() throws SQLException {
		return delegate.getAutoCommit();
	}

	@Override
	public void commit() throws SQLException {
		delegate.commit();
	}

	@Override
	public void rollback() throws SQLException {
		delegate.rollback();
	}

	@Override
	public void close() throws SQLException {
		delegate.close();
	}

	@Override
	public boolean isClosed() throws SQLException {
		return delegate.isClosed();
	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return delegate.getMetaData();
	}

	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		delegate.setReadOnly(readOnly);
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		return delegate.isReadOnly();
	}

	@Override
	public void setCatalog(String catalog) throws SQLException {
		delegate.setCatalog(catalog);
	}

	@Override
	public String getCatalog() throws SQLException {
		return delegate.getCatalog();
	}

	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		delegate.setTransactionIsolation(level);
	}

	@Override
	public int getTransactionIsolation() throws SQLException {
		return delegate.getTransactionIsolation();
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return delegate.getWarnings();
	}

	@Override
	public void clearWarnings() throws SQLException {
		delegate.clearWarnings();
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return delegate.createStatement(resultSetType, resultSetConcurrency, getResultSetHoldability());
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return delegate.prepareStatement(sql, resultSetType, resultSetConcurrency, getResultSetHoldability());
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
		return delegate.prepareCall(sql, resultSetType, resultSetConcurrency);
	}

	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return delegate.getTypeMap();
	}

	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		delegate.setTypeMap(map);
	}

	@Override
	public void setHoldability(int holdability) throws SQLException {
		delegate.setHoldability(holdability);
	}

	@Override
	public int getHoldability() throws SQLException {
		return delegate.getHoldability();
	}

	@Override
	public Savepoint setSavepoint() throws SQLException {
		return delegate.setSavepoint();
	}

	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		return delegate.setSavepoint(name);
	}

	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		delegate.rollback(savepoint);
	}

	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		delegate.releaseSavepoint(savepoint);
	}

	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return delegate.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return delegate.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return delegate.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
		return delegate.prepareStatement(sql, autoGeneratedKeys);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
		return delegate.prepareStatement(sql, columnIndexes);
	}

	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
		return delegate.prepareStatement(sql, columnNames);
	}

	@Override
	public Clob createClob() throws SQLException {
		return delegate.createClob();
	}

	@Override
	public Blob createBlob() throws SQLException {
		return delegate.createBlob();
	}

	@Override
	public NClob createNClob() throws SQLException {
		return delegate.createNClob();
	}

	@Override
	public SQLXML createSQLXML() throws SQLException {
		return delegate.createSQLXML();
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return delegate.isValid(timeout);
	}

	@Override
	public void setClientInfo(String name, String value) throws SQLClientInfoException {
		delegate.setClientInfo(name, value);
	}

	@Override
	public void setClientInfo(Properties properties) throws SQLClientInfoException {
		delegate.setClientInfo(properties);
	}

	@Override
	public String getClientInfo(String name) throws SQLException {
		return delegate.getClientInfo(name);
	}

	@Override
	public Properties getClientInfo() throws SQLException {
		return delegate.getClientInfo();
	}

	@Override
	public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
		return delegate.createArrayOf(typeName, elements);
	}

	@Override
	public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
		return delegate.createStruct(typeName, attributes);
	}

	@Override
	public void setSchema(String schema) throws SQLException {
		delegate.setSchema(schema);
	}

	@Override
	public String getSchema() throws SQLException {
		return delegate.getSchema();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		delegate.abort(executor);
	}

	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
		delegate.setNetworkTimeout(executor, milliseconds);
	}

	@Override
	public int getNetworkTimeout() throws SQLException {
		return delegate.getNetworkTimeout();
	}
	
	private int getResultSetHoldability() {
		int resultSetHoldability = ResultSet.HOLD_CURSORS_OVER_COMMIT;
		
		if (implementation == SQLDatabaseContext.SQLImplementation.SQLITE) {
			resultSetHoldability = ResultSet.CLOSE_CURSORS_AT_COMMIT; 
		}
		
		return resultSetHoldability;
	}
	
}