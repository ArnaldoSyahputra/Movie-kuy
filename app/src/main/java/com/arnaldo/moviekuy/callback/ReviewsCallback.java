package com.arnaldo.moviekuy.callback;

import com.arnaldo.moviekuy.model.Review;

import java.util.List;

public interface ReviewsCallback {

    void success(List<Review> reviews);

    void error(Exception error);

}
