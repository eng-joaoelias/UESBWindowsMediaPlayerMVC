package br.edu.musical.view;

import br.edu.musical.controller.CatalogoController;
import br.edu.musical.model.Album;
import br.edu.musical.model.Artista;
import br.edu.musical.model.Musica;

import javax.swing.*;
import java.awt.*;

public class CatalogoView extends JFrame {
    private CatalogoController controller;

    // Componentes de Cadastro de Artista
    private JTextField txtNomeArtista;

    // Componentes de Cadastro de Álbum
    private JTextField txtTituloAlbum;
    private JTextField txtAnoAlbum;
    private JComboBox<Artista> cmbArtistaAlbum;

    // Componentes de Cadastro de Música
    private JTextField txtTituloMusica;
    private JTextField txtDuracaoMusica;
    private JComboBox<Artista> cmbArtistaMusica;
    private JComboBox<Album> cmbAlbumMusica;

    // Listagem geral
    private JTextArea txtAreaListagem;

    public CatalogoView() {
        this.controller = new CatalogoController();

        setTitle("Catálogo Musical - MVC Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel Principal com Abas para organizar os cadastros e listagem
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Cadastrar Artista", criarPainelArtista());
        tabbedPane.addTab("Cadastrar Álbum", criarPainelAlbum());
        tabbedPane.addTab("Cadastrar Música", criarPainelMusica());
        tabbedPane.addTab("Listar Registros", criarPainelListagem());

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel criarPainelArtista() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nome do Artista:"), gbc);

        txtNomeArtista = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        painel.add(txtNomeArtista, gbc);

        JButton btnCadastrar = new JButton("Salvar Artista");
        gbc.gridx = 1; gbc.gridy = 1;
        painel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(e -> {
            try {
                controller.cadastrarArtista(txtNomeArtista.getText());
                JOptionPane.showMessageDialog(this, "Artista cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtNomeArtista.setText("");
                atualizarCombos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return painel;
    }

    private JPanel criarPainelAlbum() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Título do Álbum:"), gbc);
        txtTituloAlbum = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        painel.add(txtTituloAlbum, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Ano de Lançamento:"), gbc);
        txtAnoAlbum = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        painel.add(txtAnoAlbum, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Selecione o Artista:"), gbc);
        cmbArtistaAlbum = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 2;
        painel.add(cmbArtistaAlbum, gbc);

        JButton btnCadastrar = new JButton("Salvar Álbum");
        gbc.gridx = 1; gbc.gridy = 3;
        painel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(e -> {
            try {
                Artista artistaSelecionado = (Artista) cmbArtistaAlbum.getSelectedItem();
                controller.cadastrarAlbum(txtTituloAlbum.getText(), txtAnoAlbum.getText(), artistaSelecionado);
                JOptionPane.showMessageDialog(this, "Álbum cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtTituloAlbum.setText("");
                txtAnoAlbum.setText("");
                atualizarCombos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return painel;
    }

    private JPanel criarPainelMusica() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Título da Música:"), gbc);
        txtTituloMusica = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 0;
        painel.add(txtTituloMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Duração (em segundos):"), gbc);
        txtDuracaoMusica = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        painel.add(txtDuracaoMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Selecione o Artista:"), gbc);
        cmbArtistaMusica = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 2;
        painel.add(cmbArtistaMusica, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("Selecione o Álbum:"), gbc);
        cmbAlbumMusica = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 3;
        painel.add(cmbAlbumMusica, gbc);

        // Atualiza os álbuns disponíveis com base no artista selecionado
        cmbArtistaMusica.addActionListener(e -> {
            Artista artista = (Artista) cmbArtistaMusica.getSelectedItem();
            cmbAlbumMusica.removeAllItems();
            if (artista != null) {
                for (Album alb : controller.getAlbuns()) {
                    if (alb.getArtista().equals(artista)) {
                        cmbAlbumMusica.addItem(alb);
                    }
                }
            }
        });

        JButton btnCadastrar = new JButton("Salvar Música");
        gbc.gridx = 1; gbc.gridy = 4;
        painel.add(btnCadastrar, gbc);

        btnCadastrar.addActionListener(e -> {
            try {
                Artista art = (Artista) cmbArtistaMusica.getSelectedItem();
                Album alb = (Album) cmbAlbumMusica.getSelectedItem();
                controller.cadastrarMusica(txtTituloMusica.getText(), txtDuracaoMusica.getText(), art, alb);
                JOptionPane.showMessageDialog(this, "Música cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                txtTituloMusica.setText("");
                txtDuracaoMusica.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro de Validação", JOptionPane.ERROR_MESSAGE);
            }
        });

        return painel;
    }

    private JPanel criarPainelListagem() {
        JPanel painel = new JPanel(new BorderLayout(5, 5));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtAreaListagem = new JTextArea();
        txtAreaListagem.setEditable(false);
        painel.add(new JScrollPane(txtAreaListagem), BorderLayout.CENTER);

        JButton btnListar = new JButton("Atualizar Listagem");
        painel.add(btnListar, BorderLayout.SOUTH);

        btnListar.addActionListener(e -> exibirListagemGeral());

        return painel;
    }

    private void atualizarCombos() {
        // Atualiza combo de artista no cadastro de álbum
        cmbArtistaAlbum.removeAllItems();
        for (Artista a : controller.getArtistas()) {
            cmbArtistaAlbum.addItem(a);
        }

        // Atualiza combo de artista no cadastro de música
        cmbArtistaMusica.removeAllItems();
        for (Artista a : controller.getArtistas()) {
            cmbArtistaMusica.addItem(a);
        }
    }

    private void exibirListagemGeral() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== ARTISTAS CADASTRADOS ===\n");
        if (controller.getArtistas().isEmpty()) {
            sb.append("(Nenhum artista cadastrado)\n");
        } else {
            for (Artista a : controller.getArtistas()) {
                sb.append(" - ").append(a.getNome()).append("\n");
            }
        }

        sb.append("\n=== ÁLBUNS CADASTRADOS ===\n");
        if (controller.getAlbuns().isEmpty()) {
            sb.append("(Nenhum álbum cadastrado)\n");
        } else {
            for (Album al : controller.getAlbuns()) {
                sb.append(" - ").append(al.getTitulo()).append(" (").append(al.getAno()).append(") [Artista: ").append(al.getArtista().getNome()).append("]\n");
            }
        }

        sb.append("\n=== MÚSICAS CADASTRADAS ===\n");
        if (controller.getMusicas().isEmpty()) {
            sb.append("(Nenhuma música cadastrada)\n");
        } else {
            for (Musica m : controller.getMusicas()) {
                int min = m.getDuracaoSegundos() / 60;
                int seg = m.getDuracaoSegundos() % 60;
                sb.append(" - ").append(m.getTitulo()).append(" (").append(min).append(":").append(String.format("%02d", seg)).append(") | Álbum: ").append(m.getAlbum().getTitulo()).append(" | Artista: ").append(m.getArtista().getNome()).append("\n");
            }
        }

        txtAreaListagem.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CatalogoView().setVisible(true);
        });
    }
}