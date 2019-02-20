import lk.ijse.dep.fx.dao.DAOFactory;
import lk.ijse.dep.fx.dao.SuperDAO;

public class TestDAO {
    public static void main(String[] args) {
        SuperDAO dao = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
        System.out.println(dao);

        SuperDAO dao1 = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICLE);
        System.out.println(dao1);

        DAOFactory instance = DAOFactory.getInstance();
        System.out.println(instance);
        DAOFactory instance1 = DAOFactory.getInstance();
        System.out.println(instance1);
    }
}
