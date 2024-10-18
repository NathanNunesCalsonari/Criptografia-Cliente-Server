package br.uam.criptografiaclienteservidor;

import java.io.*;
import java.math.BigInteger;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8000)) {
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            DataInputStream input = new DataInputStream(socket.getInputStream());

            String mensagem = "Tranquilo!!";
            System.out.println("Enviando mensagem ao servidor: " + mensagem);
            output.writeUTF(mensagem);

            String mensagemCifrada = input.readUTF();
            System.out.println("Mensagem cifrada recebida do servidor: " + mensagemCifrada);

            RSA rsa = new RSA(new BigInteger("31"), new BigInteger("37"));
            String mensagemDecifrada = rsa.decrypt(mensagemCifrada);
            System.out.println("Mensagem decifrada no cliente: " + mensagemDecifrada);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
