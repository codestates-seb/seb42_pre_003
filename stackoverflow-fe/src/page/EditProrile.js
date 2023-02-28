import React from 'react';
import styled from 'styled-components';
import ProfileInfo from '../components/profile/ProfileInfo';
import TabMenu from '../components/profile/TabMenu';
import EditInput from '../components/profile/EditInput';

const Container = styled.section`
	min-width: 1000px;

	.contents {
		display: flex;
	}
`;

const EditProrile = () => {
	return (
		<Container>
			<ProfileInfo />
			<div className='contents'>
				<TabMenu />
				<EditInput />
			</div>
		</Container>
	);
};

export default EditProrile;
