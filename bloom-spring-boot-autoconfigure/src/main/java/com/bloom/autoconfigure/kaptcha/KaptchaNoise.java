package com.bloom.autoconfigure.kaptcha;

import com.google.code.kaptcha.NoiseProducer;
import com.google.code.kaptcha.util.Configurable;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 干扰线配置
 *
 * @author taosy
 * @version V1.0
 * @since 2022-01-05 16:12
 */
public class KaptchaNoise extends Configurable implements NoiseProducer {
    public KaptchaNoise() {
    }

    @Override
    public void makeNoise(BufferedImage image, float factorOne, float factorTwo, float factorThree, float factorFour) {
        Color color = this.getConfig().getNoiseColor();
        int width = image.getWidth();
        int height = image.getHeight();
        Point2D[] pts = null;
        Random rand = new Random();
        CubicCurve2D cc = new CubicCurve2D.Float((float) width * factorOne, (float) height * rand.nextFloat(),
            (float) width * factorTwo, (float) height * rand.nextFloat(), (float) width * factorThree,
            (float) height * rand.nextFloat(), (float) width * factorFour, (float) height * rand.nextFloat());
        PathIterator pi = cc.getPathIterator((AffineTransform) null, 2.0D);
        Point2D[] tmp = new Point2D[200];
        int i = 0;

        while (!pi.isDone()) {
            float[] coords = new float[6];
            int currentSegment = pi.currentSegment(coords);
            if (currentSegment == 0 || currentSegment == 1) {
                tmp[i] = new Point2D.Float(coords[0], coords[1]);
            }
            ++i;
            pi.next();
        }

        pts = new Point2D[i];
        System.arraycopy(tmp, 0, pts, 0, i);
        Graphics2D graph = (Graphics2D) image.getGraphics();
        graph.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
        graph.setColor(color);

        for (i = 0; i < pts.length - 1; ++i) {
            if (i < 3) {
                graph.setStroke(new BasicStroke(2.5F * (float) (4 - i)));
            }
            graph.drawLine((int) pts[i].getX(), (int) pts[i].getY(), (int) pts[i + 1].getX(), (int) pts[i + 1].getY());
        }
        graph.dispose();
    }
}
