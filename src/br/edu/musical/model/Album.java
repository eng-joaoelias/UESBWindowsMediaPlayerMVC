package br.edu.musical.model;

public class Album {
	private String titulo;
	private int ano;
	private Artista artista;
	
	public Album(String titulo, Artista artista, int ano) {
		this.ano = ano;
		this.artista = artista;
		this.titulo = titulo;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Artista getArtista() {
		return artista;
	}
	public void setArtista(Artista artista) {
		this.artista = artista;
	}
	
	
}
