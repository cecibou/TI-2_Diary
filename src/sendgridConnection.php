<?php
   
    require 'vendor/autoload.php';

   
    $email = new \SendGrid\Mail\Mail();
$email->setFrom("967132@sga.pucminas.br", "Equipe Project Diary");
$email->setSubject($_POST['assunto']);
$email->addTo($_POST['email'], $_POST['nome']);
switch($_POST['assunto']){
case 'Quero investir':
$email->addContent("text/html", "<p>Caro(a) {$_POST['nome']}!<br>Estamos felizes que esteja considerando investir com nossa equipe,em breve vamos entrar em contato pelo  numero {$_POST['DDD']}-{$_POST['telefone']} para conversamos melhor.<br>Obrigado,<br>Equipe Project Diary</p>");
break;
case 'Assunto importante':
$email->addContent("text/html", "<p>Caro(a) {$_POST['nome']},<br>Encaminharemos a sua mensagem para nosso suporte,em breve vamos entrar em contato pelo  numero {$_POST['DDD']}-{$_POST['telefone']} para conversamos sobre o assunto e te ajudar a resolve-lo.<br>Obrigado,<br>Equipe Project Diary</p>");
break;
case'Patrocinio':
$email->addContent("text/html", "<p>Caro(a) {$_POST['nome']},<br>Muito Obrigado pelo seu interesse em apoiar nossa equipe,em breve vamos entrar em contato pelo  numero {$_POST['DDD']}-{$_POST['telefone']} para conversamos melhor.<br>Obrigado,<br>Equipe Project Diary</p>");
break;
case 'Reuniao':
$email->addContent("text/html", "<p>Caro(a) {$_POST['nome']},<br>Estamos cientes de seu pedido para um encontro conosco,em breve vamos entrar em contato pelo  numero {$_POST['DDD']}-{$_POST['telefone']} para definirmos uma data.<br>Obrigado,<br>Equipe Project Diary</p>");
break;
default:
$email->addContent("text/html", "<p>Caro(a) {$_POST['nome']},<br>Sua mensagem foi recebida,porem foi enviada sem assunto.<br>Assim,talvez demore um pouco mais para responde-la.<br>Sem mais,<br>Equipe Project Diary</p>");
break;
}
$sendgrid = new \SendGrid('');
try {
    $response = $sendgrid->send($email);
    print $response->statusCode() . "\n";
    print_r($response->headers());
    print $response->body() . "\n";
} catch (Exception $e) {
    echo 'Caught exception:',  $e->getMessage(), "\n";
}
header("Location: https://projeto-diary.netlify.app/contacts.html ");
    ?>
