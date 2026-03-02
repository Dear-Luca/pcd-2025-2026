package pcd.lab03.deadlock_simplest;

public class Resource {
	
	private String name;
	
	public Resource(String name) {
		this.name = name;
	}
	
	public void use(){
		System.out.println("using res: " + name);
	}
	
}
