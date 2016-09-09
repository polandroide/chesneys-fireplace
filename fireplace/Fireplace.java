/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 


import javax.swing.JTable;

/**
 *
 * @author Paul Love
 * @version 1.0
 * 
 * This class is used to access all the data 
 * concerning the fireplaces in the company's stock
 */

public class Fireplace extends Db
{
    int fireplace_id;
    String fireName;
    String material;
    String powerType;
    String colour;
    String companyName;
    float price;
    int supplierId;
    int quantity;
    
    /**
     * Retrieves all the fireplaces in stock
     * through the getTable method in the database class
     * @return JTable with all stock fireplaces
     */
    public JTable getAllSupplierTable()
    {
        return getTable("SELECT * FROM `fireplace`");
    }
    
    /**
     * Creates a query to retrieve the first result
     * in the database of the fireplace by name
     * @param fireName part of the fireplace name
     */
    public void findByFireName(String fireName){
        String sql = "SELECT * FROM `fireplace` WHERE `fire_name` LIKE '%" + fireName + "%'";
        findBy(sql);
    }
    
    /**
     * Creates a query to retrieve the first result
     * in the database of the fireplace by id
     * @param supplierId part of the fireplace name
     */
    public void findById(String supplierId){
        String sql = "SELECT * FROM `fireplace` WHERE `fireplace_id` = " + supplierId;
        findBy(sql);
    }
    /**
     * Retrieves the data and instantiates
     * a fireplace object
     * @param sql one of the above queries create
     * in this class
     */
    private void findBy(String sql){
        setConnection();
        executeQuery(sql);
        try{
            if (resultSet.next()){
                instantiateFireplace();
            }else{
                fireName = "0";
            }
            closeConnection();
            if (!fireName.equals("0")){
                setCompanyName();
            }
        }catch(Exception ex){
            closeConnection();
            fireName = "0";
            message = "Couldn't findBy " + ex.getMessage();
            showError();
        }
    }
    /**
     * saves an instantiated object to the database
     * @return boolean - to indicate whether the
     * save was successful
     */
    public Boolean save(){
        int companyId = getCompanyId();
        String sql = "INSERT INTO `fireplace` (" +
                "fire_name, material, power_type, colour, price, quantity, supplier_id ) VALUES ('" +
                fireName + "', '" + material + "', '" + powerType + "', '" + colour + "', " +
                price + " , " + quantity + ", " + companyId + ")";
        setConnection();
        if(executeUpdate(sql)){
            closeConnection();
            return true;
        }else{
            closeConnection();
            return false;
        }
    }
    /**
     * A database query to add the number
     * of new fireplace orders to the
     * existing quantity
     * @param quantity - the quantity new orders
     * @return - boolean to check whether the
     * query was successful
     */
    public boolean addQuantity(int quantity){
        quantity += getQuantity();
        this.quantity = quantity;
        String sql = "UPDATE `fireplace` SET `quantity` = " + quantity + " WHERE `fireplace_id` = " + fireplace_id;
        
        setConnection();
        if(executeUpdate(sql)){
            closeConnection();
            return true;
        }else{
            closeConnection();
            return false;
        }
    }
    
    /**
     * A method to update the data of a fireplace
     * @return boolean to check whether
     * the update was successful
     */
    public boolean update(){
        
        int companyId = getCompanyId();
        String sql = "UPDATE `fireplace` SET " +
                "fire_name = '" + fireName + "'," +
                "material = '" + material + "', " +
                "power_type = '" + powerType + "', " +
                "colour = '" + colour + "', " +
                "price = " + price + ", " +
                "quantity = " + quantity + ", " +
                "supplier_id = " + companyId +
                " WHERE fireplace_id = " + fireplace_id;
        setConnection();
        if(executeUpdate(sql)){
            closeConnection();
            return true;
        }else{
            closeConnection();
            return false;
        }
    }
    
    /**
     * This is method used to get the company name from
     * the supplier_id that the fireplace has.
     * 
     * @return the Id of the company
     */
    private int getCompanyId(){
        int companyId = 0;
        String sql = "SELECT `supplier_id` FROM `supplier` WHERE `company_name` = '" + companyName + "'";
        setConnection();
        if(executeQuery(sql)){
            companyId = getFirstInt();
        }else{
            closeConnection();
        }
        return companyId;
    }
    /**
     * Sets the company name of the fireplace
     * using the supplier_id
     */
    private void setCompanyName(){
        String companyName = "";
        String sql = "SELECT `company_name` FROM `supplier` WHERE `supplier_id` = " + supplierId;
        setConnection();
        if(executeQuery(sql)){
            companyName = getFirstString();
            closeConnection();
        }else{
            closeConnection();
        }
        this.companyName = companyName;
    }
    /**
     * A method that instantiates a fireplace object
     */
    private void instantiateFireplace(){
        try {
            fireplace_id = resultSet.getInt("fireplace_id");
            fireName = resultSet.getString("fire_name");
            material = resultSet.getString("material");
            powerType = resultSet.getString("power_type");
            colour = resultSet.getString("colour");
            price = resultSet.getFloat("price");
            quantity = resultSet.getInt("quantity");
            supplierId = resultSet.getInt("supplier_id");
            
        }catch(Exception ex){
            fireName = "0";
            message = "<HTML>Couldn't instantiate fireplace in Fireplace.java<br />" + ex.getMessage();
            showError();
        }
    }
    
    /**
     * Sets the company name of an instantiated object
     * @param companyName 
     */
    
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    /**
     * Sets the fireplace name of an instantiated object
     * @param fireName 
     */
    public void setFireName(String fireName)
    {
        this.fireName = fireName;
    }
    /**
     * Sets the material the fireplace is made of
     * @param material - the type of material
     */
    public void setMaterial(String material)
    {
        this.material = material;
    }
    /**
     * Sets the source of the power of the fireplace
     * @param powerType - the type of power it uses
     */
    public void setPowerType(String powerType)
    {
        this.powerType = powerType;
    }
    /**
     * Sets the colour of the fireplace
     * @param colour - the colour
     */
    public void setColour(String colour)
    {
        this.colour = colour;
    }
    /**
     * Sets the price of the instantiated fireplace
     * @param price - the cost of the fireplace
     */
    public void setPrice(float price)
    {
        this.price = price;
    }
    /**
     * Sets the supplier's id of the fireplace
     * @param supplierId - the supplier's id
     */
    public void setSupplierId(int supplierId)
    {
        this.supplierId = supplierId;
    }
    /**
     * Sets the quantity of fireplaces
     * @param quantity - the number of fireplaces
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    /**
     * @return - the id of the fireplace
     */
    public int getFireplaceId()
    {
        return this.fireplace_id;
    }
    /**
     * 
     * @return - the name of the fireplace
     */
    public String getFireName()
    {
        return this.fireName;
    }
    /**
     * 
     * @return - the colour of the fireplace
     */
    public String getColour(){
        return this.colour;
    }
    /**
     * 
     * @return - the source of power of the fireplace
     */
    public String getPowerType()
    {
        return this.powerType;
    }
    /**
     * 
     * @return - the material the fireplace is made of
     */
    public String getMaterial()
    {
        return this.material;
    }
    /**
     * 
     * @return - the supplier id of the fireplace
     */
    public int getSupplierId()
    {
        return this.supplierId;
    }
    /**
     * 
     * @return the price of the fireplace
     */
    public float getPrice()
    {
        return this.price;
    }
    /**
     * 
     * @return the quantity of fireplaces
     */
    public int getQuantity()
    {
        return this.quantity;
    }
    /**
     * 
     * @return the company that makes the fireplaces
     */
    public String getCompanyName(){
        return this.companyName;
    }
    
}
