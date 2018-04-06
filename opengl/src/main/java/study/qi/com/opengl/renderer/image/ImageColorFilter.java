package study.qi.com.opengl.renderer.image;

import android.content.Context;
import android.opengl.GLES20;

/**
 * Created by feng on 2018/3/10.
 */

public class ImageColorFilter extends AbstractFilter {
    private Filter mFilter;

    public ImageColorFilter(Context context, Filter filter) {
        super(context, "filter/half_color_vertex.frag", "filter/half_color_fragment.frag");
        mFilter = filter;
    }

    @Override
    public void onDrawDataChange() {
        int vChangeTypeHandle = GLES20.glGetUniformLocation(mProgram, "vChangeType");
        GLES20.glUniform1i(vChangeTypeHandle, mFilter.getType());
        int vChangeDataHandle = GLES20.glGetUniformLocation(mProgram, "vChangeData");
        GLES20.glUniform3fv(vChangeDataHandle, 1, mFilter.data(), 0);

    }

    public enum Filter {

        NONE(0, new float[]{0.0f, 0.0f, 0.0f}),
        GRAY(1, new float[]{0.299f, 0.587f, 0.114f}),
        COOL(2, new float[]{0.0f, 0.0f, 0.1f}),
        WARM(2, new float[]{0.1f, 0.1f, 0.0f}),
        BLUR(3, new float[]{0.006f, 0.004f, 0.002f}),
        MAGN(4, new float[]{0.0f, 0.0f, 0.4f});


        private int vChangeType;
        private float[] data;

        Filter(int vChangeType, float[] data) {
            this.vChangeType = vChangeType;
            this.data = data;
        }

        public int getType() {
            return vChangeType;
        }

        public float[] data() {
            return data;
        }

    }

}
