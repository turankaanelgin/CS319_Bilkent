package gamecontrol.playercontrol;

public enum StoreItem
{
	BOMB_BALL(0, 50),
	BACK_BALL(1, 70),
	FREEZE_BALL(2, 90),
	LIVE(3, 200);
	
	private int index;
	private int price;
	
	private StoreItem( int index, int price)
	{
		this.index = index;
		this.price = price;
	}
	
	public int getIndex()
	{
		return index;
	}
	
	public int getPrice()
	{
		return price;
	}
}
