package by.epam.cinemarating.dao;

import by.epam.cinemarating.database.WrapperConnection;
import by.epam.cinemarating.entity.Entity;

import java.util.List;
import java.util.Optional;

public abstract class AbstractDAO<T extends Entity>{
	protected WrapperConnection connection;

	public AbstractDAO(WrapperConnection connection) {
		this.connection = connection;
	}

	public abstract List<T> findAll() throws DAOException;

	public abstract Optional<T> findEntityById(long id) throws DAOException;

	public abstract boolean insert(T entity) throws DAOException;

	public abstract boolean delete(long id) throws DAOException;

	public abstract boolean update(T entity) throws DAOException;
}