package dungeonProjectPackage;
import java.awt.Dimension;
import java.awt.Rectangle;


public class Entity implements Comparable<Entity> {
	
	public Dimension clickPosition;
	public int x;
	public int y;
	
	private String entityName;

	private String entityType;

	

	private int HP;
	private int AC;
	private int AttackP;
	private int dmgP;
	private int movespeed;
	private int initative;
	private int actionPoint;
	




	private int currentHP;
	private int currentAC;
	private int currentAttackP;
	private int currentdmgP;
	private int currentMovespeed;
	private int currentActionPoint;
	
	


	Rectangle bounds;

	
		public void attack(Entity target)
		{   
		int dobas = 0;
		int attackrollP = (int) (Math.random() *20 + 1);
		dobas = attackrollP;
		System.out.println(" \nThe attack roll with d20 is: " +attackrollP + "+"+this.getCurrentAttackP());
		
		if(dobas == 20)
			{
				target.getAttack(this.getCurrentdmgP()*2);
				System.out.println("Critical Hit!\n    Caused " + this.getCurrentdmgP()*2 +" damage.");
				System.out.println("\nThe target has " +target.getCurrentHP()+ " hit points left.");
			}
			else if((dobas+this.getCurrentAttackP()) >= target.getCurrentAC())
			{
										target.getAttack(this.getCurrentdmgP());
					System.out.println("Hit!\n    This caused " + this.getCurrentdmgP() +" damage.");
					System.out.println("\nThe target has " +target.getCurrentHP()+ " hit points left.");
					
			}	
			else 
			{
				System.out.println("Miss!\n");
	

			}
		}

	
	public void getAttack(int dmg){                              
		this.setCurrentHP(this.getCurrentHP()-dmg);  
		};
	
	public void currentstat()
		{
			
		this.setCurrentHP(this.getHP());
		this.setCurrentAC(this.getAC());
		this.setCurrentAttackP(this.getAttackP());
		this.setCurrentdmgP(this.getDmgP());

		}
		

	
	public  void rollInit()
		{                              
		int rand =  (int) (Math.random() *20 + 1);
		this.setInitative(rand);
		
		};
	
	public int compareTo(Entity compareEntity) {

			int compareInitative = ((Entity) compareEntity).getInitative();

			//ascending order
			//return this.initative - compareInitative;

			//descending order
			return compareInitative - this.initative;

		}
	
	public void refreshmove()
	{
		this.setCurrentActionPoint(this.getActionPoint());
		this.setCurrentMovespeed(this.getMovespeed());
	}
	
	
	public int getHP() {
		return HP;
	}





	public void setHP(int hP) {
		HP = hP;
	}





	public int getAC() {
		return AC;
	}





	public void setAC(int aC) {
		AC = aC;
	}





	public int getAttackP() {
		return AttackP;
	}





	public void setAttackP(int attackP) {
		AttackP = attackP;
	}

	public int getActionPoint() {
		return actionPoint;
	}


	public void setActionPoint(int actionPoint) {
		this.actionPoint = actionPoint;
	}

	public int getMovespeed() {
		return movespeed;
	}


	public void setMovespeed(int movespeed) {
		this.movespeed = movespeed;
	}



	public int getDmgP() {
		return dmgP;
	}





	public void setDmgP(int dmgP) {
		this.dmgP = dmgP;
	}

	public String getEntityName() {
		return entityName;
	}


	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}




	public int getCurrentHP() {
		return currentHP;
	}





	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}





	public int getCurrentAC() {
		return currentAC;
	}





	public void setCurrentAC(int currentAC) {
		this.currentAC = currentAC;
	}





	public int getCurrentAttackP() {
		return currentAttackP;
	}





	public void setCurrentAttackP(int currentAttackP) {
		this.currentAttackP = currentAttackP;
	}


	public int getInitative() {
		return initative;
	}


	public void setInitative(int initative) {
		this.initative = initative;
	}



	public int getCurrentdmgP() {
		return currentdmgP;
	}





	public void setCurrentdmgP(int currentdmgP) {
		this.currentdmgP = currentdmgP;
	}

	public String getEntityType() {
		return entityType;
	}


	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	
	



	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	

	public Rectangle getBounds() 
	{
		return bounds;
	}
	
	public void setCoords(int j,int l)
	{
		x = j;
		y = l;
	}
	
	public void setX(int j) 
	{
		x = j;
	}
	
	public void setY(int l) 
	{
		y = l;
	}
	
	public void setBounds(Rectangle b)
	{
		bounds = b;
	}
	

	public int getCurrentMovespeed() {
		return currentMovespeed;
	}


	public void setCurrentMovespeed(int currentMovespeed) {
		this.currentMovespeed = currentMovespeed;
	}


	public int getCurrentActionPoint() {
		return currentActionPoint;
	}


	public void setCurrentActionPoint(int currentActionPoint) {
		this.currentActionPoint = currentActionPoint;
	}
	
}

