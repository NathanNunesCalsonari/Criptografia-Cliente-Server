package br.uam.criptografiaclienteservidor;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            System.out.println("Servidor iniciado. Aguardando conexão...");
            Socket clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado!");

            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            br.uam.criptografiaclienteservidor.RSA rsa = new br.uam.criptografiaclienteservidor.RSA(new BigInteger("31"), new BigInteger("37")); // Modifique os valores conforme necessário

            String mensagemRecebida = input.readUTF();
            System.out.println("Mensagem recebida do cliente: " + mensagemRecebida);

            String mensagemCifrada = rsa.encrypt(mensagemRecebida);
            System.out.println("Mensagem cifrada: " + mensagemCifrada);

            output.writeUTF(mensagemCifrada);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
