package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.LogDAO;
import it.siliconsquare.model.administration.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class LogDAOImpl implements LogDAO {
    @Autowired
    private List<Log> logList;
    private String sql = "SELECT * FROM log , user , action  WHERE  log.id_user = user.id_user and log.id_action = action.id_action ";

    public LogDAOImpl() {
        logList = new ArrayList<>();
    }

    public List<Log> getAllLog() {
        return Q2ObjList.fromSelect(Log.class, sql);
    }

    @Override
    public Log getLogById(int id) {
        return Q2Obj.fromSelect(Log.class, sql + "AND id_log = ?", id);
    }

    @Override
    public Log getLogByUserID(int userID) {
        return Q2Obj.fromSelect(Log.class, sql + "AND id_user = ?", userID);
    }

    @Override
    public Log getLogByIdAction(int actionID) {
        return Q2Obj.fromSelect(Log.class, sql + "and id_action = ?", actionID);
    }

    @Override
    public void saveLog(Log l) {
        Q2Obj.insert(l);
    }
}