package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.versionpb.briskybird.BriskyDemo;

public class MenuState extends State {
	
	private Texture background;            //New Texture explained here 
    private Texture playBtn;
    
    public MenuState(GameStateManager gsm) {
		super(gsm);
		background = new Texture("playBg_menu.png");
		playBtn = new Texture("playButton.png");
		
	}
    
	@Override
	public void handleInput() {
		if(Gdx.input.justTouched()){
			gsm.push(new PlayState(gsm));
			dispose();
			}
		
	}

	@Override
	public void update(float dt) {
		handleInput();
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin(); 
		sb.draw(background, 0, 0, BriskyDemo.WIDTH, BriskyDemo.HEIGHT);
		sb.draw(playBtn,(BriskyDemo.WIDTH/2) - (playBtn.getWidth() / 2), BriskyDemo.HEIGHT/2);
		sb.end(); 
		
	}

	@Override
	public void dispose() {
		background.dispose();
        playBtn.dispose();
		
	}

	

}
