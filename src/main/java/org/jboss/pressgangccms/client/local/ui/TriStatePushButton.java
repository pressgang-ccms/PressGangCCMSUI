package org.jboss.pressgangccms.client.local.ui;

import org.jboss.pressgangccms.client.local.constants.Constants;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;

/**
 * A PushButton to perform the functionality of a tristate checkbox.
 * @author Matthew Casperson
 *
 */
public class TriStatePushButton extends PushButton
{
	private enum State
	{
		NONE, SELECTED, UNSELECTED
	}

	private State state = State.NONE;

	public TriStatePushButton()
	{
		super(new Image(Constants.resources.round16()));

		this.addClickHandler(new ClickHandler()
		{
			@Override
			public void onClick(ClickEvent event)
			{
				if (state == State.NONE)
				{
					state = State.SELECTED;
				}
				else if (state == State.SELECTED)
				{
					state = State.UNSELECTED;
				}
				else if (state == State.UNSELECTED)
				{
					state = State.NONE;
				}
				
				updateState();
			}
		});
	}

	public State getState()
	{
		return state;
	}

	public void setState(final State state)
	{
		this.state = state;
		updateState();
	}
	
	private void updateState()
	{
		if (state == State.SELECTED)
		{
			TriStatePushButton.this.getUpFace().setImage(new Image(Constants.resources.plus16()));
		}
		else if (state == State.UNSELECTED)
		{
			TriStatePushButton.this.getUpFace().setImage(new Image(Constants.resources.minus16()));
		}
		else if (state == State.NONE)
		{
			TriStatePushButton.this.getUpFace().setImage(new Image(Constants.resources.round16()));
		}
	}
}
