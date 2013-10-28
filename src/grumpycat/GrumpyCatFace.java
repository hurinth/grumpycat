package grumpycat;

/**
 *
 * @author Jose Pablo Méndez Soto
 */
import javax.swing.ImageIcon;
import logic.GPExtractor;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class GrumpyCatFace extends javax.swing.JFrame {

    JFileChooser fileChooser;
    GPExtractor gpUnzipper;

    public GrumpyCatFace() {
        initComponents();
        initComponentsCustom();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        filePathTxtLbl = new javax.swing.JTextField();
        btnBrowse = new javax.swing.JButton();
        btnExtract = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuHelp = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        itemAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Grumpycat File Extractor");

        btnBrowse.setText("Browse");
        btnBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBrowseActionPerformed(evt);
            }
        });

        btnExtract.setText("Extract Files");
        btnExtract.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtractActionPerformed(evt);
            }
        });

        menuHelp.setText("Help");

        jMenuItem2.setText("Help Contents");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuHelp.add(jMenuItem2);

        itemAbout.setText("About");
        itemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(itemAbout);

        jMenuBar1.add(menuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(filePathTxtLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnBrowse)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExtract)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(filePathTxtLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBrowse)
                    .addComponent(btnExtract))
                .addContainerGap(253, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initComponentsCustom() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        gpUnzipper = new GPExtractor();
        filePathTxtLbl.setText(fileChooser.getCurrentDirectory().toString());
        this.setLocationRelativeTo(null);
    }

    // buttons start here
    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBrowseActionPerformed
        fileChooser.showOpenDialog(GrumpyCatFace.this);
        filePathTxtLbl.setText(fileChooser.getSelectedFile().toString());
        System.out.println("Selected file:" + fileChooser.getSelectedFile().toString());
    }//GEN-LAST:event_btnBrowseActionPerformed

    private void btnExtractActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtractActionPerformed
        gpUnzipper.extractThemAll(fileChooser.getSelectedFile().toString());
    }//GEN-LAST:event_btnExtractActionPerformed

    private void itemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAboutActionPerformed
        String description = "Grumpycat\nA tool for TAC engineers."
                + "\nBy José Pablo Méndez Soto."
                + "\njmendez2@cisco.com"
                + "\nhttps://github.com/hurinth/grumpycat"
                + "\n2013";
        ImageIcon gcIcon = new ImageIcon(GrumpyCatFace.class.getResource("../media/gcp.jpg"));
        JOptionPane.showMessageDialog(this, description,
                "About this tool", JOptionPane.INFORMATION_MESSAGE, gcIcon);
    }//GEN-LAST:event_itemAboutActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String help = "Browse through your computer's filesystem and point to a"
                + "\ndirectory where you already have several trace sets stored,"
                + "\nor choose a .zip file directly."
                + "\n\nGrumpycat **does not** extract the XML files that RTMT"
                + "\ngenerates, and deletes the compressed txt.gz archives "
                + "\nleaving only the useful traces that are pure text.";
        JOptionPane.showMessageDialog(this, help,
                "Help", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrumpyCatFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrumpyCatFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrumpyCatFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrumpyCatFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrumpyCatFace().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBrowse;
    private javax.swing.JButton btnExtract;
    private javax.swing.JTextField filePathTxtLbl;
    private javax.swing.JMenuItem itemAbout;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu menuHelp;
    // End of variables declaration//GEN-END:variables
}
