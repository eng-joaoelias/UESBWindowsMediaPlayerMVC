package br.edu.musical.model;

public class Musica {
	private String titulo;
	private int duracaoSegundos;
	private Artista artista;
	private Album album;
	
	public Musica(String titulo, int duracaoSegundos, Artista artista, Album album) {
		this.album = album;
		this.artista = artista;
		this.duracaoSegundos = duracaoSegundos;
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracaoSegundos() {
		return duracaoSegundos;
	}

	public void setDuracaoSegundos(int duracaoSegundos) {
		this.duracaoSegundos = duracaoSegundos;
	}

	public Artista getArtista() {
		return artista;
	}

	public void setArtista(Artista artista) {
		this.artista = artista;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	@Override
    public String toString() {
        int minutos = duracaoSegundos / 60;
        int segundos = duracaoSegundos % 60;
        String duracaoFormatada = String.format("%d:%02d", minutos, segundos);
        return titulo + " [" + duracaoFormatada + "] - Álbum: " + album.getTitulo() + " (Artista: " + artista.getNome() + ")";
    }
}
