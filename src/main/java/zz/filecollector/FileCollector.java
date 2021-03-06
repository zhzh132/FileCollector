/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zz.filecollector;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JFileChooser;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author zhan
 */
public class FileCollector extends javax.swing.JFrame {
    
    private static final ConfigManager config = new ConfigManager();

    /**
     * Creates new form PhotoCollector
     */
    public FileCollector() {
        initComponents();
        String src = config.getSourceDir();
        if(StringUtils.isNotEmpty(src)) {
            this.setSrcDir(new File(src));
        }
        String dest = config.getDestDir();
        if(StringUtils.isNotEmpty(dest)) {
            this.setDestDir(new File(dest));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        archiveDirChooser = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        selectDestButton = new javax.swing.JButton();
        archiveDirField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        srcDirField = new javax.swing.JTextField();
        selectSrcButton = new javax.swing.JButton();
        scanButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoArea = new javax.swing.JTextArea();
        stopButton = new javax.swing.JButton();
        actionBox = new javax.swing.JComboBox<>();

        archiveDirChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("归档目录：");

        selectDestButton.setText("选择...");
        selectDestButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectDestButtonActionPerformed(evt);
            }
        });

        archiveDirField.setEditable(false);

        jLabel2.setText("照片和视频所在目录：");

        srcDirField.setEditable(false);

        selectSrcButton.setText("选择...");
        selectSrcButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectSrcButtonActionPerformed(evt);
            }
        });

        scanButton.setText("预览");
        scanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scanButtonActionPerformed(evt);
            }
        });

        startButton.setText("开始");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        infoArea.setEditable(false);
        infoArea.setBackground(new java.awt.Color(240, 240, 240));
        infoArea.setColumns(20);
        infoArea.setRows(5);
        jScrollPane1.setViewportView(infoArea);

        stopButton.setText("停止");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopButtonActionPerformed(evt);
            }
        });

        actionBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "移动到归档目录", "复制到归档目录", "移动到归档目录，同时删除重复文件" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addComponent(scanButton, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(actionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(startButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(stopButton))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(archiveDirField)
                                    .addComponent(srcDirField, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(selectDestButton)
                                    .addComponent(selectSrcButton))))))
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(srcDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectSrcButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(archiveDirField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectDestButton))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(startButton)
                        .addComponent(stopButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(actionBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scanButton)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectDestButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectDestButtonActionPerformed
        int returnVal = archiveDirChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.setDestDir(archiveDirChooser.getSelectedFile());
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_selectDestButtonActionPerformed

    private void selectSrcButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectSrcButtonActionPerformed
        int returnVal = archiveDirChooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.setSrcDir(archiveDirChooser.getSelectedFile());
        } else {
            System.out.println("File access cancelled by user.");
        }
    }//GEN-LAST:event_selectSrcButtonActionPerformed

    private void scanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scanButtonActionPerformed
        fileWorker = new FileWorkerThread(this);
        fileWorker.setActionType(FileWorkerThread.ACTION_PREVIEW);
        fileWorker.start();
    }//GEN-LAST:event_scanButtonActionPerformed

    private void stopButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopButtonActionPerformed
        if(fileWorker != null) {
            fileWorker.stopWorking();
        }
    }//GEN-LAST:event_stopButtonActionPerformed

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        fileWorker = new FileWorkerThread(this);
        fileWorker.setActionType(this.getActionType());
        fileWorker.start();
    }//GEN-LAST:event_startButtonActionPerformed

    private void setSrcDir(File srcDir) {
        this.srcDir = srcDir;
        srcDirField.setText(srcDir.getAbsolutePath());
        config.setSourceDir(srcDir.getAbsolutePath());
    }
    
    private void setDestDir(File destDir) {
        this.destDir = destDir;
        archiveDirField.setText(destDir.getAbsolutePath());
        config.setDestDir(destDir.getAbsolutePath());
    }
    
    public void disableButtons() {
        this.scanButton.setEnabled(false);
        this.startButton.setEnabled(false);
        this.selectDestButton.setEnabled(false);
        this.selectSrcButton.setEnabled(false);
        this.actionBox.setEnabled(false);
    }
    
    public void enableButtons() {
        this.scanButton.setEnabled(true);
        this.startButton.setEnabled(true);
        this.selectDestButton.setEnabled(true);
        this.selectSrcButton.setEnabled(true);
        this.actionBox.setEnabled(true);
    }
    
    public void displayInfo(String info) {
        infoArea.append(info);
        infoArea.setCaretPosition(infoArea.getDocument().getLength());
    }
    
    public int getActionType() {
        int selected = this.actionBox.getSelectedIndex();
        switch (selected) {
            case 0:
                return FileWorkerThread.ACTION_MOVE;
            case 1:
                return FileWorkerThread.ACTION_COPY;
            case 2:
                return FileWorkerThread.ACTION_MOVEDEL;
            default:
                return FileWorkerThread.ACTION_PREVIEW;
        }
    }

    public File getDestDir() {
        return destDir;
    }

    public File getSrcDir() {
        return srcDir;
    }
    
    private File destDir;
    private File srcDir;
    private FileWorkerThread fileWorker;
    
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
            java.util.logging.Logger.getLogger(FileCollector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FileCollector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FileCollector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FileCollector.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FileCollector frame = new FileCollector();
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent event) {
                        config.saveProperties();
                    }
                });
                frame.setTitle("Photo & Video Collector");
                frame.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> actionBox;
    private javax.swing.JFileChooser archiveDirChooser;
    private javax.swing.JTextField archiveDirField;
    private javax.swing.JTextArea infoArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton scanButton;
    private javax.swing.JButton selectDestButton;
    private javax.swing.JButton selectSrcButton;
    private javax.swing.JTextField srcDirField;
    private javax.swing.JButton startButton;
    private javax.swing.JButton stopButton;
    // End of variables declaration//GEN-END:variables

}
