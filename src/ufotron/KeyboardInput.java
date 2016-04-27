package ufotron;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

class KeyboardInput implements KeyListener
{
		@Override
		public void keyPressed(int i, char c)
		{
			if(UfoTron.currentState.GetCommands().containsKey(i))
				UfoTron.currentState.GetEvents().add(i);
		}
		
		@Override
		public void keyReleased(int i, char c)
		{
			
		}

		@Override
		public void setInput(Input input)
		{
			//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public boolean isAcceptingInput()
		{
			//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			return true;
		}

		@Override
		public void inputEnded()
		{
			//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}

		@Override
		public void inputStarted()
		{
			//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
		}
	}
