package hvqzao.wildcard;

import burp.BurpExtender;
import burp.IBurpExtenderCallbacks;

public class OutscopePane extends javax.swing.JPanel {

    private IBurpExtenderCallbacks callbacks;

    public OutscopePane() {
        initComponents();
        initialize();
    }

    private void initialize() {
        callbacks = BurpExtender.getCallbacks();

        helpButton.setIcon(BurpExtender.getIconHelp());
        defaultsButton.setIcon(BurpExtender.getIconDefaults());
        helpButton.setEnabled(false);
        defaultsButton.setEnabled(false);
        
        callbacks.customizeUiComponent(excludeButton);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        helpButton = new javax.swing.JButton();
        defaultsButton = new javax.swing.JButton();
        description = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        excludeButton = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 10, 5));

        helpButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        helpButton.setMaximumSize(new java.awt.Dimension(24, 24));
        helpButton.setMinimumSize(new java.awt.Dimension(24, 24));
        helpButton.setPreferredSize(new java.awt.Dimension(24, 24));

        defaultsButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        defaultsButton.setMaximumSize(new java.awt.Dimension(24, 24));
        defaultsButton.setMinimumSize(new java.awt.Dimension(24, 24));
        defaultsButton.setPreferredSize(new java.awt.Dimension(24, 24));

        description.setText("<html>Scope control features.</html>");

        title.setText("<html><b style='color:#e58900;font-size:10px'>Scope</b></html>");

        excludeButton.setText("Exclude common static files");
        excludeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excludeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(defaultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(excludeButton))))
                .addGap(0, 393, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excludeButton))
                    .addComponent(defaultsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void excludeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excludeButtonActionPerformed
        String config = callbacks.saveConfigAsJson("target.scope.exclude");
        int startIndex = config.indexOf("[");
        int closeIndex = config.lastIndexOf("]");
        BurpExtender.getStderr().println(String.valueOf(startIndex) + ", " + String.valueOf(closeIndex));
        StringBuilder configBuilder = new StringBuilder(config.substring(0, closeIndex));
        if (startIndex + 1 != closeIndex) {
            configBuilder.append(",\n");
        }
        configBuilder.append("{\n"
                + "\"enabled\":true,\n"
                + "\"file\":\"^/.*\\\\.(js|css|gif|png|jpg|jpeg|ico|svg|woff|woff2|eot|ttf)(;|\\\\?|$)\",\n"
                + "\"protocol\":\"any\"\n"
                + "}");
        configBuilder.append(config.substring(closeIndex, config.length()));
        callbacks.loadConfigFromJson(configBuilder.toString());
      }//GEN-LAST:event_excludeButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton defaultsButton;
    private javax.swing.JLabel description;
    private javax.swing.JButton excludeButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
