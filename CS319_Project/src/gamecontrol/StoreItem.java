package gamecontrol;

public enum StoreItem
{
	BOMB_BALL(0),
	BACK_BALL(1),
	FREEZE_BALL(2),
	LIVE(3);
	
	public int index;
	
	private StoreItem( int index)
	{
		this.index = index;
	}
}
