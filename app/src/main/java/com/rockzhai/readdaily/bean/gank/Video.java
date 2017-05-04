package com.rockzhai.readdaily.bean.gank;

import java.io.Serializable;
import java.util.List;

/**
 * Created by rockzhai on 2017/4/24.
 *
 */
public class Video implements Serializable {

    private boolean error;
    private List<Gank> results;

    public boolean isError() {
        return error;
    }

    public List<Gank> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "Video{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
