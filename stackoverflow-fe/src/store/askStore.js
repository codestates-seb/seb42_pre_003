import { create } from 'zustand';
import { persist, devtools } from 'zustand/middleware';

export const useBoxStore = create(
	persist(
		(set) => ({
			ableData: [
				{
					title: true,
					detail: false,
					try: false,
					tag: false,
					review: false,
				},
			],
			askData: [
				{
					title: '',
					detail: '',
					try: '',
					tag: '',
				},
			],
			setAbleData: (select) => {
				set((state) => ({ ...state, ableData: [select] }));
			},
			setAskData: (select) => {
				set((state) => ({ ...state, askData: [select] }));
			},
		}),
		{ name: 'ask-storage' },
	),
);

export const useAskStore = create(
	devtools((set) => ({
		initialAble: {
			title: true,
			detail: false,
			try: false,
			tag: false,
			review: false,
		},
		initialAsk: {
			title: '',
			detail: '',
			try: '',
			tag: '',
		},
		title: '',
		titleBind: (item) => set({ title: item }),
		titleReset: (item) => set({ title: '' }),
		detail: '',
		detailBind: (item) => set({ detail: item }),
		detailReset: (item) => set({ detail: '' }),
		expect: '',
		expectBind: (item) => set({ try: item }),
		expectReset: (item) => set({ try: '' }),
		tag: '',
		tagBind: (item) => set({ tag: item }),
		tagReset: (item) => set({ tag: '' }),
		page: 'ask',
		handlePage: () => set({ page: 'review' }),
	})),
);
