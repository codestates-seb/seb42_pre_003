import React from 'react';
import styled from 'styled-components';
import CancelButton from '../list/CancelButton';
import Editor from './Editor';
import SaveButton from './SaveButton';

const Container = styled.section`
	padding: 30px;

	h1 {
		font-size: 27px;
		width: 600px;
		height: 42px;
		border-bottom: 1px solid #bebdbd;
		margin-bottom: 32px;
	}

	h2 {
		font-size: 19px;
		margin-bottom: 10px;
	}

	.button {
		display: flex;
		margin-top: 40px;
	}
`;

const Inputbody = styled.div`
	border: 1px solid hsl(210deg 8% 75%);
	border-radius: 3px;
	padding: 20px;

	.image {
		font-weight: bold;

		.img {
			width: 150px;
			height: 150px;
			background-color: #f1f1f1;
			margin-top: 5px;
			position: relative;

			button {
				width: 150px;
				height: 32px;
				background-color: #525960;
				position: absolute;
				bottom: 0;
				color: #fff;
			}
		}
	}

	.name,
	.location,
	.tit,
	.about {
		.title {
			font-size: 15px;
			font-weight: bold;
			margin-top: 15px;
			margin-bottom: 5px;
		}

		input {
			width: 300px;
			height: 32px;
			padding: 5px;
			border: 1px solid hsl(210deg 8% 75%);
			border-radius: 3px;
		}
	}
`;

function EditInput() {
	return (
		<Container>
			<h1>Edit your Profile</h1>
			<h2>Pubilc information</h2>
			<Inputbody>
				<div className='image'>
					Profile image
					<div className='img'>
						<button>Change picture</button>
					</div>
				</div>
				<div className='name'>
					<div className='title'>Display name</div>
					<input></input>
				</div>
				<div className='location'>
					<div className='title'>Location</div>
					<input></input>
				</div>
				<div className='tit'>
					<div className='title'>Title</div>
					<input placeholder='No title has been set'></input>
				</div>
				<div className='about'>
					<div className='title'>About me</div>
					<Editor />
				</div>
			</Inputbody>
			<div className='button'>
				<SaveButton />
				<CancelButton />
			</div>
		</Container>
	);
}

export default EditInput;
