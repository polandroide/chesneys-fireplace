 
import java.util.List;
import javax.swing.*;
/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * This class inherits from the Db class to access the database to
 * instantiate one or many supplier objects. The result set has
 * a certain lifespan, while it is connected to the database, so
 * this programmer believes it would be advantagous to create
 * supplier objects that will have the freedom to create themselves
 * and to have access to their own methods for future manipulation.
 */
public class Supplier extends Db{
    
    List<Supplier> suppliers;
    
    private int supplierId;
    private String companyName;
    private String collection;
    private String phone;
    private String contact;
    private String address;
    
    /**
     * 
     * @param supplierId - creates the sql to retrieve a supplier by id
     */
    public void findById(String supplierId){
        String sql = "SELECT * FROM `supplier` WHERE `supplier_id` = " + supplierId;
        findBy(sql);
    }
    /**
     * 
     * @param address - creates the sql to retrieve a supplier by his address
     */
    public void findByCompanyAddress(String address){
        String sql = "SELECT * FROM `supplier` WHERE `address` LIKE '%" + address + "%'";
        findBy(sql);
    }
    /**
     * 
     * @param companyName - creates the sql to retrieve a supplier by the company's name
     */
    public void findByCompanyName(String companyName){
        String sql = "SELECT * FROM `supplier` WHERE `company_name` LIKE '%" + companyName + "%'";
        findBy(sql);
    }
    
    /**
     * 
     * @param sql - uses the created sql to query the Db 
     * if there is a resultSet a supplier object is instantiated
     * if not the company name is set to "0" to be picked up later
     * 
     */
    
    private void findBy(String sql){
        setConnection();
        executeQuery(sql);
        try{
            if (resultSet.next()){
                instantiateSupplier();
            }else{
                companyName = "0";
            }
            closeConnection();
        }catch(Exception ex){
            message = "Couldn't findBy " + ex.getMessage();
        }
    }
    
    /**
     * This method attempts to save the supplier object into the database
     * @return boolean - returns whether the save was successful
     */
    public Boolean save(){
        try{
            super.setConnection();
            String sql = "INSERT INTO supplier (" +
                    "company_name, address, phone, contact, collection) VALUES (" +
                    "'" + companyName + "', " +
                    "'" + address + "', " +
                    "'" + phone + "', " +
                    "'" + contact + "', " +
                    "'" + collection + "')";
            super.executeUpdate(sql);
            super.closeConnection();
            return true;
        }catch(Exception ex){
            super.message = "Couldn't save data: " + ex.getMessage();
            showError();
            return false;
        }
    }
    /**
     * attemps to update the object into the database
     */
    public void update(){
        String sql = "UPDATE `supplier` SET " + 
                " company_name = '" + this.companyName + "'," +
                " address = '" + this.address + "'," +
                " phone = '" + this.phone + "', " +
                " contact = '" + this.contact + "', " +
                " collection = '" + this.collection + "' " +
                " WHERE supplier_id = " + this.supplierId;
        
        setConnection();
        executeUpdate(sql);
        closeConnection();
    }
    /**
     * 
     * @return - a JTable object that has been populated in the Db class
     */
    public JTable getAllSupplierTable()
    {
        return getTable("SELECT * FROM `supplier`");
    }
    /**
     * a method that instantiates a supplier object with a resultSet
     */
    private void instantiateSupplier(){
        try{
            companyName = resultSet.getString("company_name");
            address = resultSet.getString("address");
            collection = resultSet.getString("collection");
            contact = resultSet.getString("contact");
            phone = resultSet.getString("phone");
            supplierId = resultSet.getInt("supplier_id");
        }catch(Exception ex){
            companyName = "0";
            message = "Couldn't instatiate supplier: " + ex.getMessage();
            showError();
        }
    }
    /**
     * 
     * @return a list of Strings of all the company names on the database
     */
    public List<String> getAllSupplierNames(){
        String sql = "SELECT `company_name` FROM `supplier` ORDER BY `company_name`";
        return getStringArrayOfResultSet(sql);
    }
    /**
     * 
     * @return a list of suppliers...unused as JTable is used instead
     */
    public List<Supplier> getSuppliers(){
        return suppliers;
    }
    /**
     * 
     * @param supplierId - sets the supplierId in the supplier object
     */
    public void setSupplierId(int supplierId){
        this.supplierId = supplierId;
    }
    
    /**
     * 
     * @param companyName - sets the companyName in the supplier object
     */
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    /**
     * 
     * @param address - sets the company address in the supplier object
     */
    public void setAddress(String address){
        this.address = address;
    }
    
    /**
     * 
     * @param phone - sets the company phone number in the supplier object
     */
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    /**
     * 
     * @param collection - sets the company fireplace collection name in the supplier object
     */
    public void setCollection(String collection){
        this.collection = collection;
    }
    
    /**
     * 
     * @param contact - sets the company contact name in the supplier object
     */
    public void setContact(String contact){
        this.contact = contact;
    }
    
    /**
     * 
     * @return supplierId - gets the supplier in the supplier object
     */
    public int getSupplierId(){
        return supplierId;
    }
    /**
     * 
     * @return the name of the company contact
     */
    public String getContact(){
        return contact;
    }
    /**
     * 
     * @return the company's phone number
     */
    public String getPhone(){
        return phone;
    }
    /**
     * 
     * @return the name of the fireplace collection for the supply company
     */
    public String getCollection(){
        return collection;
    }
    /**
     * 
     * @return the address of the company
     */
    public String getAddress(){
        return address;
    }
    /**
     * 
     * @return the name of the company
     */
    public String getCompanyName(){
        return companyName;
    }
    
}
