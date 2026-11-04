package br.edu.musical.controller;

import br.edu.musical.model.Artista;
import br.edu.musical.model.Album;
import br.edu.musical.model.Musica;

import java.awt.List;
import java.util.ArrayList;

public class CatalogoController {
	private ArrayList<Artista> artistas;
	private ArrayList<Album> albuns;
	private ArrayList<Musica> musicas;
	
	public CatalogoController() {
		this.artistas = new ArrayList<>();
		this.albuns = new ArrayList<>();
		this.musicas = new ArrayList<>();
	}
	
	public void cadastrarArtista(String nome) throws IllegalArgumentException{
		if (nome == null || nome.trim().isEmpty()) {
			throw new IllegalArgumentException("Informe o nome do artista! Não pode ser vazio.");
		}
		
		for(Artista a : artistas) { //artista ja existente
			if (a.getNome().equalsIgnoreCase(nome.trim())) {
				throw new IllegalArgumentException("Já existe um artista cadastrado com este nome.");
			}
		}
		artistas.add(new Artista(nome.trim()));
	}
	
	public void cadastrarAlbum(String titulo, String anoStr, Artista artista) throws IllegalArgumentException{
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("Informe o nome do álbum! Não pode ser vazio.");
		}
		if (artista == null) {
			throw new IllegalArgumentException("Informe o nome do artista! Não pode ser vazio.");
		}
		int ano;
		
		try {
			ano = Integer.parseInt(anoStr);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Ano precisa ser inteiro!");
		}
		
		for(Album a : albuns) { //album ja existente
			if (a.getTitulo().equalsIgnoreCase(titulo.trim())) {
				throw new IllegalArgumentException("Já existe um álbum cadastrado com este titulo.");
			}
		}
		albuns.add(new Album(titulo.trim(), artista, ano));
	}
	
	public void cadastrarMusica(String titulo, String duracaoStr, Artista artista, Album album) throws IllegalArgumentException{
		if (titulo == null || titulo.trim().isEmpty()) {
			throw new IllegalArgumentException("Informe o nome da música! Não pode ser vazio.");
		}
		if (artista == null) {
			throw new IllegalArgumentException("Informe o nome do artista! Não pode ser vazio.");
		}
		if (album == null) {
			throw new IllegalArgumentException("Informe o nome do album! Não pode ser vazio.");
		}
		if (!album.getArtista().equals(artista)) {
            throw new IllegalArgumentException("O álbum selecionado não pertence ao artista informado!");
        }
		int duracao;
		
		try {
			duracao = Integer.parseInt(duracaoStr.trim());
			if (duracao <= 0) {
				throw new IllegalArgumentException("A duração da música precisa ser positiva! (Ex.: 12 segundos)");
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("A duração em segundos deve ser um número inteiro positivo.");
		}
		
		
		musicas.add(new Musica(titulo.trim(), duracao, artista, album));	
	}
	
	public ArrayList<Artista> getArtistas(){
		return artistas;
	}
	
	public ArrayList<Album> getAlbuns(){
		return albuns;
	}
	
	public ArrayList<Musica> getMusicas(){
		return musicas;
	}
	
}
