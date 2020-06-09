package it.polito.tdp.food.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.food.db.FoodDao;

public class Model {

	private Graph<String, DefaultWeightedEdge> grafo;
	private List<String> listaPorzioni;
	private FoodDao dao;
	
	public void creaGrafo(double Calories) {
		
		this.dao = new FoodDao();
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		this.listaPorzioni = new ArrayList<>(this.dao.listPortionFormCalories(Calories));
		
		Graphs.addAllVertices(this.grafo, this.listaPorzioni);
		this.dao.adiacenze(this.grafo);
		
	}
	
	public int getNumVertex(){
		return this.grafo.vertexSet().size();
	}
	
	public int getNumEdges(){
		return this.grafo.edgeSet().size();
	}

	public List<String> getVertici() {
		return this.listaPorzioni;
	}

	public String getAdiacenti(String value) {
		
		List<String> temp = new ArrayList<>(Graphs.neighborListOf(this.grafo, value));
		String result = "";
		
		if(temp.size()>0) {
			
			for(String p: temp) {
				result += "Porzione: "+p+", peso: "+this.grafo.getEdgeWeight(this.grafo.getEdge(value, p))+"\n";
			}
		}else {
			result = "Nessuna adiacenza trovata!";
		}
		return result;
	}
	
	public String cercaPercorsoMax(String partenza, int passi) {
		
		Ricorsione ric = new Ricorsione();
		String result =  ric.getCamminoMax(partenza, this.grafo, passi);
		return result;
	}
}
