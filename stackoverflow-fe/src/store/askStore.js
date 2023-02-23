import { create } from 'zustand';
import { persist } from 'zustand/middleware';

const useAskStore = create(
	persist(
		(set) => ({
			testData: [],
			setTestData: (select) => {
				set((state) => ({ ...state, testData: select }));
			},
		}),
		{ name: 'able-storage' },
	),
);

export default useAskStore;
