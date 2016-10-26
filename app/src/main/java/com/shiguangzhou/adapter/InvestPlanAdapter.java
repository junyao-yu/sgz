package com.shiguangzhou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.shiguangzhou.R;
import com.shiguangzhou.third.GridSLM;
import com.shiguangzhou.third.LayoutManager;
import com.shiguangzhou.third.LinearSLM;
import com.shiguangzhou.third.SectionAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Auth：yujunyao
 * Since: 2016/10/25 15:44
 * Email：yujunyao@yonglibao.com
 */

public class InvestPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements SectionAdapter<InvestPlanAdapter.Section>{


    private static final int VIEW_TYPE_HEADER = 0x01;

    private static final int VIEW_TYPE_CONTENT = 0x00;


    private final Section mSectionGraph = new Section();

    private int mHeaderDisplay;

    private boolean mMarginsFixed = false;

    private final Context mContext;


    public InvestPlanAdapter(Context context, int headerMode) {
        mContext = context;

        final String[] countryNames = context.getResources().getStringArray(R.array.country_names);
        mHeaderDisplay = headerMode;

        //Insert headers into list of items.
        String lastHeaderText = "";
        int sectionManager = -1;
        int headerCount = 0;
        int sectionFirstPosition = 0;
        Section currentSection = new Section();
        for (int i = 0; i < countryNames.length; i++) {
            String headerText = countryNames[i].substring(0, 1);
            if (!TextUtils.equals(lastHeaderText, headerText)) {
                // Insert new header view and update section data.
//                sectionManager = (sectionManager + 1) % 2;//0-
                sectionFirstPosition = i + headerCount;//1-
                lastHeaderText = headerText;
                headerCount += 1;//1
                currentSection.setEnd(sectionFirstPosition - 1);
                currentSection = new Section(sectionFirstPosition);
                currentSection.addHeader(
                        new LineItem(headerText, true, sectionManager, sectionFirstPosition));
                mSectionGraph.addSubsection(currentSection);
                Log.e("start--->", sectionFirstPosition + "");
                Log.e("end--->", sectionFirstPosition - 1 + "");
                Log.e("separator--->", "===========");
            }
            currentSection.addItem(
                    new LineItem(countryNames[i], false, sectionManager, sectionFirstPosition));
            Log.e("sectionManager--->", sectionManager + "");
        }
        currentSection.setEnd(mSectionGraph.getCount());
        Log.e("lastend--->", mSectionGraph.getCount() + "");
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_head, parent, false);
            return new InvestPlanItem1(view);
        } else {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_content, parent, false);
            return new InvestPlanItem2(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final View itemView = holder.itemView;
        final LineItem item = mSectionGraph.getItem(position);
        if(holder instanceof InvestPlanItem1) {
            InvestPlanItem1 investPlanItem1 = (InvestPlanItem1) holder;
        }else if(holder instanceof InvestPlanItem2) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click--->" + position, Toast.LENGTH_SHORT).show();
                }
            });
        }

        final GridSLM.LayoutParams lp = new GridSLM.LayoutParams(
                itemView.getLayoutParams());
        // Overrides xml attrs, could use different layouts too.
        if (item.isHeader) {
            lp.headerDisplay = mHeaderDisplay;
            if (lp.isHeaderInline() || (mMarginsFixed && !lp.isHeaderOverlay())) {
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            } else {
                lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            }
        }

        if (position == item.sectionFirstPosition) {
//            lp.setSlm(item.sectionManager == LINEAR ? LinearSLM.ID : GridSLM.ID);
            lp.setSlm(LinearSLM.ID);
            lp.marginEnd = mMarginsFixed ? mContext.getResources()
                    .getDimensionPixelSize(R.dimen.default_section_marginEnd)
                    : LayoutManager.LayoutParams.MARGIN_AUTO;
            lp.marginStart = mMarginsFixed ? mContext.getResources()
                    .getDimensionPixelSize(R.dimen.default_section_marginStart)
                    : LayoutManager.LayoutParams.MARGIN_AUTO;
            lp.setColumnWidth(mContext.getResources().getDimensionPixelSize(R.dimen.grid_column_width));
        }

        itemView.setLayoutParams(lp);

//        itemView.setBackgroundResource(R.color.colorAccent);
    }

    @Override
    public int getItemCount() {
        return mSectionGraph.getCount();
    }

    @Override
    public List<Section> getSections() {
        return mSectionGraph.getSubsections();
    }

    @Override
    public int getItemViewType(int position) {
        return mSectionGraph.getItem(position).isHeader ? VIEW_TYPE_HEADER : VIEW_TYPE_CONTENT;
    }

//    public void setHeaderDisplay(int headerDisplay) {
//        mHeaderDisplay = headerDisplay;
//        notifyHeaderChanges();
//    }
//
//    private void notifyHeaderChanges() {
//        final int count = mSectionGraph.getCount();
//        for (int i = 0; i < count; i++) {
//            final LineItem item = mSectionGraph.getItem(i);
//            if (item.isHeader) {
//                notifyItemChanged(i);
//            }
//        }
//        notifyDataSetChanged();
//    }

    /**********************************************************************************************************************************************/

    private static class LineItem {

        public int sectionManager;

        public int sectionFirstPosition;

        public boolean isHeader;

        public String text;

        public LineItem(String text, boolean isHeader, int sectionManager,
                        int sectionFirstPosition) {
            this.isHeader = isHeader;
            this.text = text;
            this.sectionManager = sectionManager;
            this.sectionFirstPosition = sectionFirstPosition;
        }
    }

    public static class Section extends SectionAdapter.Section<Section> {

        private ArrayList<LineItem> mItems = new ArrayList<>();

        private LineItem mHeader;

        public Section() {
        }

        public Section(int start) {
            super(start);
        }

        public Section addHeader(LineItem header) {
            mHeader = header;
            return this;
        }

        public Section addItem(LineItem item) {
            mItems.add(item);
            return this;
        }

        public Section addSubsection(Section section) {
            if (subsections == null) {
                subsections = new ArrayList<>();
            }
            subsections.add(section);
            return this;
        }

        public int getCount() {
            int sum = mHeader == null ? 0 : 1;
            if (subsections != null && subsections.size() != 0) {
                for (Section sub : subsections) {
                    sum += sub.getCount();
                }
            } else {
                sum += mItems.size();
            }

            return sum;
        }

        public LineItem getItem(int position) {
            if (mHeader != null && position == start) {
                return mHeader;
            }

            if (subsections != null) {
                for (Section sub : subsections) {
                    if (sub.contains(position)) {
                        return sub.getItem(position);
                    }
                }
            }

            return mItems.get(position - start - (mHeader != null ? 1 : 0));
        }

        private boolean contains(int position) {
            return start <= position && position <= end;
        }
    }

}
