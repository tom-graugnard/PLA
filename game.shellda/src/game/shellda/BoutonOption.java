package game.shellda;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class BoutonOption {
		BufferedImage m_sprite;
		int m_w, m_h;
		int m_x, m_y;
		int m_nrows, m_ncols;
		float m_scale;
		BufferedImage[] m_sprites;
		Model m_model;
		boolean visible=true;

		BoutonOption(Model model, int no, BufferedImage sprite, int rows, int columns, int x, int y, float scale) {
			m_model = model;
			m_sprite = sprite;
			m_ncols = columns;
			m_nrows = rows;
			m_x = x;
			m_y = y;
			m_scale = scale;
			splitSprite();
		}

		/*
		 * This is about splitting the animated sprite into the basic sub-images
		 * constituting the animation.
		 */
		void splitSprite() {
			int width = m_sprite.getWidth(null);
			int height = m_sprite.getHeight(null);
			m_sprites = new BufferedImage[m_nrows * m_ncols];
			m_w = width / m_ncols;
			m_h = height / m_nrows;
			for (int i = 0; i < m_nrows; i++) {
				for (int j = 0; j < m_ncols; j++) {
					int x = j * m_w;
					int y = i * m_h;
					m_sprites[(i * m_ncols) + j] = m_sprite.getSubimage(x, y, m_w, m_h);
				}
			}
		}

		void step(long now) {

		}
		
		boolean inside(float x, float y) {
			float scale_mw = m_w * Options.BoutonOptionScale;
			float scale_mh = m_h * Options.BoutonOptionScale;
			if (x >= m_x && x < (m_x + scale_mw) && y >= m_y && y < (m_y + scale_mh))
				return true;
			return false;
		}
		
		/**
		 * paints this square on the screen.
		 * 
		 * @param g
		 */
		void paint(Graphics g) {

			Image img = m_sprites[0];
			int w = (int) (m_scale * m_w);
			int h = (int) (m_scale * m_h);
			g.drawImage(img, m_x, m_y, w, h, null);

		}
}
