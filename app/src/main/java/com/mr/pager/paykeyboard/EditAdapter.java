package com.mr.pager.paykeyboard;



import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class EditAdapter extends BaseQuickAdapter<EditAdapter.EditBean, BaseViewHolder> {
    public EditAdapter(@Nullable List<EditBean> data) {
        super(R.layout.item_pay_edit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, EditBean item) {
        helper.setText(R.id.tv_num, item.title);
    }

    public static class EditBean {
        String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}

