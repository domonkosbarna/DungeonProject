package dungeonProjectPackage;

public class Monster extends Entity
{

	Monster (int hp, int ac, int attack, int dmg){ //constructor - 
		this.setHP(hp);
		this.setAC(ac);
		this.setAttackP(attack);
		this.setDmgP(dmg);
	};
	

	Monster (){
		this.setHP(20);
		this.setAC(14);
		this.setAttackP(3);
		this.setDmgP(5);
		this.setActionPoint(1);
		this.setMovespeed(7);
		
		
		this.setEntityName("Monster");
		this.setEntityType("Enemy");
		
		this.currentstat();
	};
	
	
	
}
