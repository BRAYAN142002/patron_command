
package co.unicauca.openmarket.presentation;


//import co.unicauca.openmarket.client.domain.Category;
import co.unicauca.openmarket.domain.Category;
import co.unicauca.openmarket.domain.service.CategoryService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reloj.frameworkobsobs.Observador;

/**
 *
 * @author Libardo Pantoja
 */
public class GUICategoriesFind extends javax.swing.JDialog implements Observador {
    private CategoryService categoryService;
    /**
     * Creates new form GUIProductsFind
     */
    public GUICategoriesFind(java.awt.Frame parent, boolean modal,CategoryService categoryService) {
        super(parent, modal);
        initComponents();
        initializeTable();
        this.categoryService = categoryService;
        setLocationRelativeTo(null); //centrar al ventana
    }
    
    private void initializeTable() {
        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Name"
                }
        ));
    }
  
    
    private void fillTable(List<Category> listCategories) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[2];//No columnas
        for (int i = 0; i < listCategories.size(); i++) {
            rowData[0] = listCategories.get(i).getCategoryId();
            rowData[1] = listCategories.get(i).getName();
            
            model.addRow(rowData);
        }
    }
    private void fillTableId(Category category){
      initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();
       
        Object rowData[] = new Object[2];//No columnas
        rowData[0] =category.getCategoryId();
        rowData[1] =category.getName();
    
       
        model.addRow(rowData);  
    }
    private void fillTableName(List<Category> listCategories) {
        initializeTable();
        DefaultTableModel model = (DefaultTableModel) tblProducts.getModel();

        Object rowData[] = new Object[2];//No columnas
        for (int i = 0; i < listCategories.size(); i++) {
            rowData[0] = listCategories.get(i).getCategoryId();
               
            rowData[1] = listCategories.get(i).getName();

            
            model.addRow(rowData);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducts = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JButton();
        pnlNorth = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        rdoId = new javax.swing.JRadioButton();
        rdoName = new javax.swing.JRadioButton();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        btnSearchAll = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Búsqueda de categorias");

        pnlCenter.setLayout(new java.awt.BorderLayout());

        tblProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProducts);

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        btnClose.setText("Cerrar");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });
        jPanel1.add(btnClose);

        pnlCenter.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(pnlCenter, java.awt.BorderLayout.CENTER);

        jLabel1.setText("Buscar por:");
        pnlNorth.add(jLabel1);

        buttonGroup1.add(rdoId);
        rdoId.setSelected(true);
        rdoId.setText("Id");
        rdoId.setName("rdId"); // NOI18N
        pnlNorth.add(rdoId);

        buttonGroup1.add(rdoName);
        rdoName.setText("Nombre de la categoria");
        rdoName.setName("rdoName"); // NOI18N
        rdoName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNameActionPerformed(evt);
            }
        });
        pnlNorth.add(rdoName);

        txtSearch.setName("txtSearch"); // NOI18N
        txtSearch.setPreferredSize(new java.awt.Dimension(62, 32));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        pnlNorth.add(txtSearch);

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearch);

        btnSearchAll.setText("Buscar Todos");
        btnSearchAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchAllActionPerformed(evt);
            }
        });
        pnlNorth.add(btnSearchAll);

        getContentPane().add(pnlNorth, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSearchAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchAllActionPerformed
        fillTable( categoryService.findAllCategories());
    }//GEN-LAST:event_btnSearchAllActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
          try{
               if(this.rdoId.isSelected()==true){                 
                    fillTableId(categoryService.findCategoryById(Long.valueOf(this.txtSearch.getText())) );
                 }else{
                    fillTableName (categoryService.findCategoriesByName(this.txtSearch.getText())); 
             }
          }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(null,
                "Envia la informacion correspondiente",
                "Error tipo de dato",
                JOptionPane.ERROR_MESSAGE);
          }catch(Exception e){
              JOptionPane.showMessageDialog(null,
                "Seleccione por el dato que quiere buscar",
                "Error al introducir el dato",
                JOptionPane.ERROR_MESSAGE);
          }
            
      
          
    }//GEN-LAST:event_btnSearchActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void rdoNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNameActionPerformed

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchAll;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlNorth;
    private javax.swing.JRadioButton rdoId;
    private javax.swing.JRadioButton rdoName;
    private javax.swing.JTable tblProducts;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
       try {
            fillTable(categoryService.findAllCategories());
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(null,
                "ERROR",
                "Error al actualizar las categorias",
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
