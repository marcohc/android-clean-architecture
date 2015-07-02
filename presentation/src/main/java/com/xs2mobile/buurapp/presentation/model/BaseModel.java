package com.xs2mobile.buurapp.presentation.model;

import com.marcohc.helperoid.ParserHelper;
import com.xs2mobile.buurapp.presentation.util.Constants;
import com.xs2mobile.buurapp.presentation.view.impl.adapter.viewholder.ViewHolderAbstractClass;

public abstract class BaseModel {

    public abstract ViewHolderAbstractClass getDefaultViewHolder(Constants.DATA_TYPE itemType);

    public String toJsonString() {
        return ParserHelper.toJsonString(this);
    }

}
