import { useState } from 'react';

export function useAskInput(initial) {
	const [value, setValue] = useState(initial);

	const bind = {
		value,
		onChange: (e) => setValue(e.target.value),
	};

	const reset = () => {
		setValue('');
	};

	return [value, bind, reset];
}

export function useAskEditor(initial) {
	const [value, setValue] = useState(initial);

	const bind = {
		value,
		onChange: (con) => setValue(con),
	};

	const reset = () => {
		setValue('');
	};

	return [value, bind, reset];
}
