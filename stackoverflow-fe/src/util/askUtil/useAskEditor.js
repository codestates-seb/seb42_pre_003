import { useState } from 'react';

export default function useAskEditor(initial) {
	const [value, setValue] = useState(initial);

	const bind = {
		onChange: (con) => setValue(con),
	};

	const reset = () => {
		setValue('');
	};

	return [value, bind, reset];
}
