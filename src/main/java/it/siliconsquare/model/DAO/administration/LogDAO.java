package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Log;

import java.util.List;

public interface LogDAO {
    public List<Log> getAllLog();

    Log getLogById(int id);

    Log getLogByUserID(int userID);

    Log getLogByIdAction(int actionPerformed);

    void saveLog(Log l);
}