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
					expect: '',
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
			expect: '',
			tag: '',
		},
		title: '',
		titleBind: (item) => set({ title: item }),
		titleReset: () => set({ title: '' }),
		detail: '',
		detailBind: (item) => set({ detail: item }),
		detailReset: () => set({ detail: '' }),
		expect: '',
		expectBind: (item) => set({ expect: item }),
		expectReset: () => set({ expect: '' }),
		tag: '',
		tagBind: (item) => set({ tag: item }),
		tagReset: () => set({ tag: '' }),
		body: '',
		bodyBind: (item) => set({ body: item }),
		bodyReset: () => set({ body: '' }),
		page: 'ask',
		handlePage: () => set({ page: 'review' }),
	})),
);
