package ufotron;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

/**
 * Custom key listener
 */
class KeyboardInput implements KeyListener
{
	/**
	 * Is called when keyboard key was pressed
	 * @param i key code
	 * @param c character corresponding to press key
	 */
		@Override
		public void keyPressed(int i, char c)
		{
			if(UfoTron.currentState.GetCommands().containsKey(i))
				UfoTron.currentState.GetEvents().add(i);
		}
		
		/**
		 * Needed to implement KeyListener interface. 
		 * @param i key code
		 * @param c character corresponding to press key
		 */
		@Override
		public void keyReleased(int i, char c)
		{
		}

		/**
		 * Needed to implement KeyListener interface. 
		 * @param i key code
		 * @param c character corresponding to press key
		 */
		@Override
		public void setInput(Input input)
		{
		}

		/**
		 * Needed to implement KeyListener interface. 
		 * @param i key code
		 * @param c character corresponding to press key
		 */
		@Override
		public boolean isAcceptingInput()
		{
			return true;
		}

		/**
		 * Needed to implement KeyListener interface. 
		 * @param i key code
		 * @param c character corresponding to press key
		 */
		@Override
		public void inputEnded()
		{
		}

		/**
		 * Needed to implement KeyListener interface. 
		 * @param i key code
		 * @param c character corresponding to press key
		 */
		@Override
		public void inputStarted()
		{
		}
	}
