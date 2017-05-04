package com.atmecs.pojo;

public class Event{
	private int event_id;
	private String name;
	private String type;
	private String description;
	
	private String presenter;
	
	public Event()
	{
		this(0,"","","","");
	}

	public Event(int event_id, String name, String type, String description
			, String presenter)
	{
		super();
		this.event_id = event_id;
		this.name = name;
		this.type = type;
		this.description = description;
		
		this.presenter = presenter;
	}

	public int getEvent_id()
	{
		return event_id;
	}

	public void setEvent_id(int event_id)
	{
		this.event_id = event_id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}



	public String getPresenter()
	{
		return presenter;
	}

	public void setPresenter(String presenter)
	{
		this.presenter = presenter;
	}

	@Override
	public String toString()
	{
		return "Event [event_id=" + event_id + ", name=" + name + ", type=" + type + ", description=" + description
				+ ", presenter="
				+ presenter + "]";
	}
	
}
