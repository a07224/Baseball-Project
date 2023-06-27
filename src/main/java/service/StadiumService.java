package service;

import db.DBConnection;
import model.stadium.Stadium;
import model.stadium.StadiumDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StadiumService {
    private Connection connection = DBConnection.getInstance();
    private StadiumDAO stadiumDAO = new StadiumDAO(connection);

    public void createStadium(String name) throws SQLException {
        stadiumDAO.createStaduim(name);
    }

    public List<Stadium> AllStadiumList() throws SQLException{
        return stadiumDAO.getAllStadium();
    }
//        try {
//            stadiumDAO.createStaduim("잠실");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//        try {
//            List<Stadium> stadiumList = stadiumDAO.getAllStadium();
//            System.out.println(stadiumList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
}
