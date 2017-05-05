$(function(){
	var modal = $('#modalCadastroRapidoEstilo');
	var botaoSalvar = modal.find('.js-modal-cadastro-estilo-salvar-btn');
	var conteinerMensagemErro = $('.js-mensagem-cadastro-rapido-estilo');
	
	//função para não submeter o modal ao apertar o enter.
	var form = modal.find('form');
	form.on('submit', function(event){event.preventDefault()});
	
	//pegando a url atribuida ao action do form.
	var url = form.attr('action')
	
	//pegando o input do form pelo id
	var inputNomeEstilo = $('#nomeEstilo')
	
	
	//Pegando o evento lançado pelo modal ao carregar
	modal.on('shown.bs.modal', onModalShow)
	
	//Pegando o evento de fechamento do modal
	modal.on('hide.bs.modal', onModalClose)
	
	//Chamado uma função através do click do botão salvar
	botaoSalvar.on('click', onBotaoSalvarClick)
	
	//Colocando o focus no input do form
	function onModalShow() {
		inputNomeEstilo.focus();
	}
	
	//Deixando o valor do input vázio.
	function onModalClose(){
		inputNomeEstilo.val('');
		conteinerMensagemErro.addClass('hidden');
		form.find('.form-group').removeClass('has-error');
	}
	
	function onBotaoSalvarClick() {		
		var nomeEstilo = inputNomeEstilo.val().trim();
		$.ajax({
			url:url,
			method: 'POST',
			contentType:'application/json',
			data: JSON.stringify({nome : nomeEstilo}),
			error: onErroSalvandoEstilo,
			success: onEstiloSalvo,
		});
	}
	
	function onErroSalvandoEstilo(obj){		
		var mensagemErro = obj.responseText;
		conteinerMensagemErro.removeClass('hidden');
		conteinerMensagemErro.html('<span>' + mensagemErro + '</span>');
		form.find('.form-group').addClass('has-error');
	}
	
	function onEstiloSalvo(estilo) {	
		var comboEstilo = $('#estilo');
		comboEstilo.append('<option value=' + estilo.codigo + '>' + estilo.nome + '</option>');
		comboEstilo.val(estilo.codigo);
		modal.modal('hide');
	}
	
	
	
});
