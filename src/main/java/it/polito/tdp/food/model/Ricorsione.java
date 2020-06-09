package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Ricorsione {
	
	private Graph<String, DefaultWeightedEdge> grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
	private List<String> bestPercorso;
	private double bestPeso = 0.0;
	private int passi;

	public String getCamminoMax(String partenza, Graph<String, DefaultWeightedEdge> g, int passi) {
		
		String result = "Miglior percorso trovato:\n";
		this.bestPercorso = new ArrayList<>();
		this.grafo = g;
		List<String> parziale = new ArrayList<>();
		this.passi=passi;
		
		parziale.add(partenza);
		cerca(parziale, 0.0, partenza);
		
		for(String s: this.bestPercorso) {
			result += s+"\n";
		}
		result+="Peso del percorso: "+this.bestPeso;
		return result;
	}
	
	private void cerca(List<String> parziale, double pesoParziale, String prec) {
		
		if(pesoParziale>this.bestPeso) {
			this.bestPercorso = new ArrayList<>(parziale);
			this.bestPeso = pesoParziale;
		}
		
		if(parziale.size()==this.passi) {
			return;
		}
		
		List<String> adiacenti = new ArrayList<>(Graphs.neighborListOf(this.grafo, prec));
		for(String ad: adiacenti) {
			
			if(!parziale.contains(ad)) {
				
				parziale.add(ad);
				cerca(parziale, (pesoParziale+this.grafo.getEdgeWeight(this.grafo.getEdge(prec, ad))), ad);
				
				parziale.remove(ad);
			}
		}
	}

}
