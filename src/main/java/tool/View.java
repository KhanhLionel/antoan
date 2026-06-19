package tool;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class View extends JFrame {

    private JTextField txtPrivateKeyPath, txtPublicKeyPath;
    private JTextField txtContractPath, txtSignPrivateKeyPath, txtSignatureOutputPath;
    private JTextArea taSignaturePreview;
    private JLabel lblStatus;

    private PrivateKey currentPrivateKey;
    private PublicKey currentPublicKey;
    private GenKey logic;

    public View() {
        logic = new GenKey();

        setTitle("Tool kí đơn hàng");
        setSize(550, 580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));
//	        JPanel pnlHeader = new JPanel(new GridLayout(2, 1));
//	        pnlHeader.setBackground(new Color(41, 128, 195));
//	        pnlHeader.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
//	        JLabel lblTitle = new JLabel("DIGITAL SIGNATURE TOOL", JLabel.CENTER);
//	        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
//	        lblTitle.setForeground(Color.WHITE);
//	        JLabel lblSub = new JLabel("Hệ thống ký số ", JLabel.CENTER);
//	        lblSub.setFont(new Font("Arial", Font.ITALIC, 12));
//	        lblSub.setForeground(Color.WHITE);
//	        pnlHeader.add(lblTitle);
//	        pnlHeader.add(lblSub);
//	        add(pnlHeader, BorderLayout.NORTH);

        JPanel pnlMain = new JPanel();
        pnlMain.setLayout(new BoxLayout(pnlMain, BoxLayout.Y_AXIS));
        pnlMain.setBorder(BorderFactory.createEmptyBorder(10, 14, 6, 14));


        JPanel pnlGenKey = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        pnlGenKey.setBorder(createCard("1. Tạo Cặp Khóa Mới"));
        pnlGenKey.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        JButton btnGenerate = new JButton("Tạo Cặp Khóa");
        btnGenerate.setFont(new Font("Arial", Font.BOLD, 13));
        btnGenerate.setBackground(new Color(41, 128, 185));
        btnGenerate.setForeground(Color.BLACK);
        btnGenerate.setFocusPainted(false);
        btnGenerate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlGenKey.add(btnGenerate);
        pnlMain.add(pnlGenKey);
        pnlMain.add(Box.createVerticalStrut(8));


        JPanel pnlExport = new JPanel(new GridBagLayout());
        pnlExport.setBorder(createCard("2. Xuất Khóa Ra File"));
        pnlExport.setMaximumSize(new Dimension(Integer.MAX_VALUE, 90));
        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.insets = new Insets(4, 4, 4, 4);

        g.gridx = 0; g.gridy = 0; g.weightx = 0;
        pnlExport.add(new JLabel("Private Key:"), g);
        g.gridx = 1; g.weightx = 1.0;
        txtPrivateKeyPath = new JTextField("private.key");
        txtPrivateKeyPath.setEditable(false);
        txtPrivateKeyPath.setBackground(new Color(240, 240, 240));
        pnlExport.add(txtPrivateKeyPath, g);
        g.gridx = 2; g.weightx = 0;
        JButton btnBrowseSavePriv = new JButton("Chọn");
        pnlExport.add(btnBrowseSavePriv, g);
        g.gridx = 3;
        JButton btnSavePrivate = new JButton("Lưu");
        styleBtn(btnSavePrivate, new Color(52, 152, 219));
        pnlExport.add(btnSavePrivate, g);

        g.gridx = 0; g.gridy = 1; g.weightx = 0;
        pnlExport.add(new JLabel("Public Key:"), g);
        g.gridx = 1; g.weightx = 1.0;
        txtPublicKeyPath = new JTextField("public.key");
        txtPublicKeyPath.setEditable(false);
        txtPublicKeyPath.setBackground(new Color(240, 240, 240));
        pnlExport.add(txtPublicKeyPath, g);
        g.gridx = 2; g.weightx = 0;
        JButton btnBrowseSavePub = new JButton("Chọn");
        pnlExport.add(btnBrowseSavePub, g);
        g.gridx = 3;
        JButton btnSavePublic = new JButton("Lưu");
        styleBtn(btnSavePublic, new Color(52, 152, 219));
        pnlExport.add(btnSavePublic, g);

        pnlMain.add(pnlExport);
        pnlMain.add(Box.createVerticalStrut(8));


        JPanel pnlSign = new JPanel(new GridBagLayout());
        pnlSign.setBorder(createCard("3. Ký File Đơn Hàng"));
        pnlSign.setMaximumSize(new Dimension(Integer.MAX_VALUE, 170));
        GridBagConstraints gr = new GridBagConstraints();
        gr.fill = GridBagConstraints.HORIZONTAL;
        gr.insets = new Insets(4, 4, 4, 4);

        gr.gridx = 0; gr.gridy = 0; gr.weightx = 0;
        pnlSign.add(new JLabel("File đơn hàng:"), gr);
        gr.gridx = 1; gr.weightx = 1.0;
        txtContractPath = new JTextField();
        txtContractPath.setEditable(false);
        txtContractPath.setBackground(new Color(240, 240, 240));
        pnlSign.add(txtContractPath, gr);
        gr.gridx = 2; gr.weightx = 0;
        JButton btnBrowseJson = new JButton("Chọn");
        pnlSign.add(btnBrowseJson, gr);

        gr.gridx = 0; gr.gridy = 1; gr.weightx = 0;
        pnlSign.add(new JLabel("Private Key:"), gr);
        gr.gridx = 1; gr.weightx = 1.0;
        txtSignPrivateKeyPath = new JTextField();
        txtSignPrivateKeyPath.setEditable(false);
        txtSignPrivateKeyPath.setBackground(new Color(240, 240, 240));
        pnlSign.add(txtSignPrivateKeyPath, gr);
        gr.gridx = 2; gr.weightx = 0;
        JButton btnBrowsePrivate = new JButton("Chọn");
        pnlSign.add(btnBrowsePrivate, gr);

        gr.gridx = 0; gr.gridy = 2; gr.weightx = 0;
        pnlSign.add(new JLabel("Lưu chữ ký tại:"), gr);
        gr.gridx = 1; gr.weightx = 1.0;
        txtSignatureOutputPath = new JTextField("contract_signature.sig");
        txtSignatureOutputPath.setEditable(false);
        txtSignatureOutputPath.setBackground(new Color(240, 240, 240));
        pnlSign.add(txtSignatureOutputPath, gr);
        gr.gridx = 2; gr.weightx = 0;
        JButton btnBrowseSig = new JButton("Chọn");
        pnlSign.add(btnBrowseSig, gr);

        gr.gridx = 0; gr.gridy = 3; gr.gridwidth = 3; gr.weightx = 1.0;
        gr.insets = new Insets(8, 4, 4, 4);
        JButton btnSign = new JButton("THỰC HIỆN KÝ SỐ");
        btnSign.setFont(new Font("Arial", Font.BOLD, 14));
        styleBtn(btnSign, new Color(39, 174, 96));
        btnSign.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        pnlSign.add(btnSign, gr);

        pnlMain.add(pnlSign);
        pnlMain.add(Box.createVerticalStrut(8));
        JPanel pnlPreview = new JPanel(new BorderLayout());
        pnlPreview.setBorder(createCard("4. Chữ Ký Số Sinh Ra (Base64)"));
        taSignaturePreview = new JTextArea(5, 20);
        taSignaturePreview.setLineWrap(true);
        taSignaturePreview.setEditable(false);
        taSignaturePreview.setFont(new Font("Courier New", Font.PLAIN, 11));
        taSignaturePreview.setBackground(new Color(245, 245, 245));
        pnlPreview.add(new JScrollPane(taSignaturePreview), BorderLayout.CENTER);
        pnlMain.add(pnlPreview);

        add(new JScrollPane(pnlMain), BorderLayout.CENTER);

        btnGenerate.addActionListener(e -> {
            try {
                KeyPair kp = logic.genkey();
                currentPrivateKey = kp.getPrivate();
                currentPublicKey = kp.getPublic();
                JOptionPane.showMessageDialog(this, "Sinh cặp khóa thành công!");
            } catch (Exception ex) {
                showError("Lỗi sinh khóa: " + ex.getMessage());
            }
        });

        btnBrowseSavePriv.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chọn vị trí lưu Private Key");
            fc.setSelectedFile(new File("private.key"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                txtPrivateKeyPath.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnSavePrivate.addActionListener(e -> {
            try {
                if (currentPrivateKey == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng bấm 'Sinh Cặp Khóa' trước!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                File file = new File(txtPrivateKeyPath.getText());
                logic.savePrivateKey(currentPrivateKey, file);
                JOptionPane.showMessageDialog(this, "Lưu Private Key thành công!");
            } catch (Exception ex) {
                showError("Lỗi lưu file: " + ex.getMessage());
            }
        });

        btnBrowseSavePub.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chọn vị trí lưu Public Key");
            fc.setSelectedFile(new File("public.key"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                txtPublicKeyPath.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnSavePublic.addActionListener(e -> {
            try {
                if (currentPublicKey == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng bấm 'Tạo Cặp Khóa' trước!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                File file = new File(txtPublicKeyPath.getText());
                logic.savePublicKey(currentPublicKey, file);

                JOptionPane.showMessageDialog(this, "Lưu Public Key thành công!");
            } catch (Exception ex) {
                showError("Lỗi lưu file: " + ex.getMessage());
            }
        });

        btnBrowseJson.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chọn file đơn hàng JSON");
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                txtContractPath.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnBrowsePrivate.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chọn file Private Key");
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
                txtSignPrivateKeyPath.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnBrowseSig.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Chọn vị trí lưu file chữ ký");
            fc.setSelectedFile(new File("contract_signature.sig"));
            if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
                txtSignatureOutputPath.setText(fc.getSelectedFile().getAbsolutePath());
        });

        btnSign.addActionListener(e -> {
            try {
                String jsonPath = txtContractPath.getText().trim();
                String keyPath = txtSignPrivateKeyPath.getText().trim();
                String outputPath = txtSignatureOutputPath.getText().trim();

                if (jsonPath.isEmpty() || keyPath.isEmpty() || outputPath.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ: file đơn hàng, private key và nơi lưu chữ ký!", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                PrivateKey privateKey = logic.importPrivateKey(keyPath);
                String base64Signature = logic.signFile(jsonPath, privateKey);
                Files.writeString(new File(outputPath).toPath(), base64Signature, StandardCharsets.UTF_8);

                taSignaturePreview.setText(base64Signature);

                JOptionPane.showMessageDialog(this, "Ký đơn hàng thành công!\nFile chữ ký: " + outputPath);

            } catch (Exception ex) {
                showError("Lỗi ký số: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void styleBtn(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
    }

    private TitledBorder createCard(String title) {
        return BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199), 1),
                title, TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 12), new Color(41, 128, 185)
        );
    }
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new View().setVisible(true));
    }
}
