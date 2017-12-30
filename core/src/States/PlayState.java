package States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.versionpb.briskybird.BriskyDemo;

import Sprites.Bird;

public class PlayState extends State {
	private Bird bird;		//add this line
	private Texture background;  

	public PlayState(GameStateManager gsm) {
		super(gsm);
		cam.setToOrtho(false, BriskyDemo.WIDTH / 2, BriskyDemo.HEIGHT / 2);
		bird = new Bird(50,50);
		
		background = new Texture("playBg_menu.png");
	}

	@Override
	public void handleInput() {
		if(Gdx.input.justTouched())
            bird.jump();
    
		
	}

	@Override
	public void update(float dt) {
		// TODO Auto-generated method stub
		handleInput();
        bird.update(dt);
		
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin(); 
		sb.draw(background, 0, 0, BriskyDemo.WIDTH, BriskyDemo.HEIGHT);
		//sb.draw(bird,50,50);
		sb.draw(bird.getTexture(), bird.getPosition().x,bird.getPosition().y);
		sb.end(); 
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
