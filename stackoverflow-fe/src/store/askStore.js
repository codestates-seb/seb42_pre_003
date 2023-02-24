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

const raw = JSON.parse(localStorage.getItem('ask-storage'));
const data = raw.state.askData[0];

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
		title: `${data.title}`,
		titleBind: (item) => set({ title: item }),
		titleReset: () => set({ title: '' }),
		detail: `${data.detail}`,
		detailBind: (item) => set({ detail: item }),
		detailReset: () => set({ detail: '' }),
		expect: `${data.expect}`,
		expectBind: (item) => set({ expect: item }),
		expectReset: () => set({ expect: '' }),
		tag: `${data.tag}`,
		tagBind: (item) => set({ tag: item }),
		tagReset: () => set({ tag: '' }),
		body: `${data.detail + data.expect}`,
		bodyBind: (item) => set({ body: item }),
		bodyReset: () => set({ body: '' }),
		page: 'ask',
		handlePage: () => set({ page: 'review' }),
	})),
);
