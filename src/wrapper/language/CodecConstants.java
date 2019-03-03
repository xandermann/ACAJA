package wrapper.language;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import wrapper.runtime.global.SystemRequests;

/**
 * [ CLASSE POUR ACCEDER AUX CODECS SUPPORTES PAR FFMPEG. ]
 * 
 * Ceci est une classe concrete "sterile", c-a-d qu'aucune classe ne peut 
 * en heriter ( d'ou la presence du final devant class).
 * 
 * Auteurs du projet : 
 * @author HUBLAU Alexandre, PAMIERI Adrien, DA SILVA CARMO Alexandre, et CHEVRIER Jean-christophe.
 */
public final class CodecConstants {
	public final static String[] ALL_EXTENSIONS = {".3g2",".3gp",".asf",".avi",".flv",".m4v",".mov",".mkv",".mp2",".mp4",".mpeg",".mpg",".ogg",".webm",".wmv"};
	public final static String[] ALL_SUPPORTED_VIDEO_CODECS = SystemRequests.askVideoCodecs();
	public final static String[] ALL_SUPPORTED_AUDIO_CODECS = SystemRequests.askAudioCodecs();
	// TODO : searches & verifications .aaf ; .svi (MPEG4 USING SPECIAL HEADERS /!\
	// TODO : check if "multi" formats (avi, mkv &  asf) are really compatible with all codecs
	
	// List who associates file extensions (key) with an hashmap containing compatible audio/video codecs
	public final static Map<String, Map<String,String>> CORRESPONDING_EXTENSION;
	static {
		
		
		Map<String, Map<String,String>> initialize_extensions = new HashMap<String,Map<String,String>>();
		Map<String, String> compatible_codecs = new HashMap<String,String>();
		for(String extension : ALL_EXTENSIONS) {
			switch(extension){
			case ".3g2":
				//mpeg4 
				compatible_codecs.put("mpeg4","aac");
				compatible_codecs.put("mpeg4","amr_nb");
				compatible_codecs.put("mpeg4","amr_wb");
				compatible_codecs.put("mpeg4","evrc");
				compatible_codecs.put("mpeg4","smv");
				//h263
				compatible_codecs.put("h263", "aac");
				compatible_codecs.put("h263", "amr_nb");
				compatible_codecs.put("h263", "amr_wb");
				compatible_codecs.put("h263", "evrc");
				compatible_codecs.put("h263", "smv");
				
				//h264
				compatible_codecs.put("h264", "aac");
				compatible_codecs.put("h264", "amr_nb");
				compatible_codecs.put("h264", "amr_wb");
				compatible_codecs.put("h264", "evrc");
				compatible_codecs.put("h264", "smv");
				break;
			case ".3gp":
				//mpeg4 
				compatible_codecs.put("mpeg4","aac");
				compatible_codecs.put("mpeg4","amr_nb");
				compatible_codecs.put("mpeg4","amr_wb");
				//h263
				compatible_codecs.put("h263", "aac");
				compatible_codecs.put("h263", "amr_nb");
				compatible_codecs.put("h263", "amr_wb");
				//h264
				compatible_codecs.put("h264", "aac");
				compatible_codecs.put("h264", "amr_nb");
				compatible_codecs.put("h264", "amr_wb");
				break;
			//case ".aaf": break;
			case ".asf":
				//multi = compatible with all codecs 
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
					for(String audioCodec : ALL_SUPPORTED_AUDIO_CODECS)
						compatible_codecs.put(videoCodec, audioCodec);
				break;
			case ".avi":
				//multi = compatible with all codecs
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
					for(String audioCodec : ALL_SUPPORTED_AUDIO_CODECS)
						compatible_codecs.put(videoCodec, audioCodec);
				break;
			case ".flv":
				//h264
				compatible_codecs.put("h264", "aac");
				compatible_codecs.put("h264", "mp3");
				break;
			case ".m4v":
				//Try others audio, multiple unknown (1)
				//h264
				compatible_codecs.put("h264","aac");
				compatible_codecs.put("h264","mp3");
				//mpeg4
				compatible_codecs.put("mpeg4","aac");
				compatible_codecs.put("mpeg4","mp3");
				//mpeg2
				compatible_codecs.put("mpeg2video","aac");
				compatible_codecs.put("mpeg2video","mp3");
				//mpeg1
				compatible_codecs.put("mpeg1video","aac");
				compatible_codecs.put("mpeg1video","mp3");
				break;
			case ".mov":
				//mjpeg
				compatible_codecs.put("mjpeg","aac");
				compatible_codecs.put("mjpeg","mp3");
				//mpeg1video
				compatible_codecs.put("mpeg1video","aac");
				compatible_codecs.put("mpeg1video","mp3");
				break;
			case ".mkv":
				//multi = compatible with all codecs
				for(String videoCodec : ALL_SUPPORTED_VIDEO_CODECS)
					for(String audioCodec : ALL_SUPPORTED_AUDIO_CODECS)
						compatible_codecs.put(videoCodec, audioCodec);
				break;
			case ".mp2":
				//mpeg1
				compatible_codecs.put("mpeg1video","mp1");
				compatible_codecs.put("mpeg1video","mp2");
				compatible_codecs.put("mpeg1video","mp3");
				break;
			case ".mp4":
				//Try others audio, multiple unknown (2)
				//h264
				compatible_codecs.put("h264","aac");
				compatible_codecs.put("h264","mp3");
				//mpeg4
				compatible_codecs.put("mpeg4","aac");
				compatible_codecs.put("mpeg4","mp3");
				//mpeg2
				compatible_codecs.put("mpeg2video","aac");
				compatible_codecs.put("mpeg2video","mp3");
				//mpeg1
				compatible_codecs.put("mpeg1video","aac");
				compatible_codecs.put("mpeg1video","mp3");
				break;
			case ".mpeg":
				//mpeg1
				compatible_codecs.put("mpeg1video","mp1");
				compatible_codecs.put("mpeg1video","mp2");
				compatible_codecs.put("mpeg1video","mp3");
				compatible_codecs.put("mpeg1video","aac");	
				break;
			case ".mpg":
				//mpeg1
				compatible_codecs.put("mpeg1video","mp1");
				compatible_codecs.put("mpeg1video","mp2");
				compatible_codecs.put("mpeg1video","mp3");
				break;
			case ".ogg":
				//theora
				compatible_codecs.put("theora","flac");
				compatible_codecs.put("theora","vorbis");
				//dirac
				compatible_codecs.put("dirac","flac");
				compatible_codecs.put("dirac","vorbis");
				break;
			//case ".svi": break;
			case ".webm":
				//vp8
				compatible_codecs.put("vp8","vorbis");
				compatible_codecs.put("vp8","opus");
				//vp9
				compatible_codecs.put("vp9","vorbis");
				compatible_codecs.put("vp9","opus");
				//av1
				compatible_codecs.put("av1","vorbis");
				compatible_codecs.put("av1","opus");
				break;
			case ".wmv":
				//wmv1
				compatible_codecs.put("wmv1","wmav1");
				compatible_codecs.put("wmv1","wmav2");
				//wmv2
				compatible_codecs.put("wmv2","wmav1");
				compatible_codecs.put("wmv2","wmav2");
				//wmv3
				compatible_codecs.put("wmv3","wmav1");
				compatible_codecs.put("wmv3","wmav2");
				//wmv3image
				compatible_codecs.put("wmv3image","wmav1");
				compatible_codecs.put("wmv3image","wmav2");
				break;
				
			}
			initialize_extensions.put(extension,compatible_codecs);
			compatible_codecs = new HashMap<String,String>();
		}
		CORRESPONDING_EXTENSION = Collections.unmodifiableMap(initialize_extensions);
	}
}


//------------------------------------------------------------------
//------------------------------------------------------------------
// TEMPORARY LIST TO CREATE CODECS-EXTENSIONS ASSOCIATION,
// PLEASE DO NOT REMOVE
//------------------------------------------------------------------
//------------------------------------------------------------------
/*	CODECS VIDEO : 
012v
4xm
8bps
a64_multi
a64_multi5
aasc
aic
alias_pix
amv
anm
ansi
apng
asv1
asv2
aura
aura2
av1
avrn
avrp
avs
avs2
avui
ayuv
bethsoftvid
bfi
binkvideo
bintext
bitpacked
bmp
bmv_video
brender_pix
c93
cavs
cdgraphics
cdxl
cfhd
cinepak
clearvideo
cljr
cllc
cmv
cpia
cscd
cyuv
daala
dds
dfa
dirac
dnxhd
dpx
dsicinvideo
dvvideo
dxa
dxtory
dxv
escape124
escape130
exr
ffv1
ffvhuff
fic
fits
flashsv
flashsv2
flic
flv1
fmvc
fraps
frwu
g2m
gdv
gif
h261
h263
h263i
h263p
h264
hap
hevc
hnm4video
hq_hqa
hqx
huffyuv
idcin
idf
iff_ilbm
imm4
indeo2
indeo3
indeo4
indeo5
interplayvideo
jpeg2000
jpegls
jv
kgv1
kmvc
lagarith
ljpeg
loco
m101
mad
magicyuv
mdec
mimic
mjpeg
mjpegb
mmvideo
motionpixels
mpeg1video
mpeg2video
mpeg4
msa1
mscc
msmpeg4v1
msmpeg4v2
msmpeg4v3
msrle
mss1
mss2
msvideo1
mszh
mts2
mvc1
mvc2
mwsc
mxpeg
nuv
paf_video
pam
pbm
pcx
pgm
pgmyuv
pictor
pixlet
png
ppm
prores
prosumer
psd
ptx
qdraw
qpeg
qtrle
r10k
r210
rasc
rawvideo
rl2
roq
rpza
rscc
rv10
rv20
rv30
rv40
sanm
scpr
screenpresso
sgi
sgirle
sheervideo
smackvideo
smc
smvjpeg
snow
sp5x
speedhq
srgc
sunrast
svg
svq1
svq3
targa
targa_y216
tdsc
tgq
tgv
theora
thp
tiertexseqvideo
tiff
tmv
tqi
truemotion1
truemotion2
truemotion2rt
tscc
tscc2
txd
ulti
utvideo
v210
v210x
v308
v408
v410
vb
vble
vc1
vc1image
vcr1
vixl
vmdvideo
vmnc
vp3
vp5
vp6
vp6a
vp6f
vp7
vp8
vp9
wcmv
webp
wmv1
wmv2
wmv3
wmv3image
wnv1
wrapped_avframe
ws_vqa
xan_wc3
xan_wc4
xbin
xbm
xface
xpm
xwd
y41p
ylc
yop
yuv4
zerocodec
zlib
zmbv */


/* ------------------------------------------------------------------
 							clipboard buffer
 * ------------------------------------------------------------------
wmv1
wmv2
wmv3
wmav1
wmav2

 * ------------------------------------------------------------------
 */


/* CODECS AUDIO : 
4gv
8svx_exp
8svx_fib
aac
aac_latm
ac3
adpcm_4xm
adpcm_adx
adpcm_afc
adpcm_aica
adpcm_ct
adpcm_dtk
adpcm_ea
adpcm_ea_maxis_xa
adpcm_ea_r1
adpcm_ea_r2
adpcm_ea_r3
adpcm_ea_xas
adpcm_g722
adpcm_g726
adpcm_g726le
adpcm_ima_amv
adpcm_ima_apc
adpcm_ima_dat4
adpcm_ima_dk3
adpcm_ima_dk4
adpcm_ima_ea_eacs
adpcm_ima_ea_sead
adpcm_ima_iss
adpcm_ima_oki
adpcm_ima_qt
adpcm_ima_rad
adpcm_ima_smjpeg
adpcm_ima_wav
adpcm_ima_ws
adpcm_ms
adpcm_mtaf
adpcm_psx
adpcm_sbpro_2
adpcm_sbpro_3
adpcm_sbpro_4
adpcm_swf
adpcm_thp
adpcm_thp_le
adpcm_vima
adpcm_xa
adpcm_yamaha
alac
amr_nb
amr_wb
ape
aptx
aptx_hd
atrac1
atrac3
atrac3al
atrac3p
atrac3pal
atrac9
avc
binkaudio_dct
binkaudio_rdft
bmv_audio
celt
codec2
comfortnoise
cook
dolby_e
dsd_lsbf
dsd_lsbf_planar
dsd_msbf
dsd_msbf_planar
dsicinaudio
dss_sp
dst
dts
dvaudio
eac3
evrc
flac
g723_1
g729
gremlin_dpcm
gsm
gsm_ms
iac
ilbc
imc
interplay_dpcm
interplayacm
mace3
mace6
metasound
mlp
mp1
mp2
mp3
mp3adu
mp3on4
mp4als
musepack7
musepack8
nellymoser
opus
paf_audio
pcm_alaw
pcm_bluray
pcm_dvd
pcm_f16le
pcm_f24le
pcm_f32be
pcm_f32le
pcm_f64be
pcm_f64le
pcm_lxf
pcm_mulaw
pcm_s16be
pcm_s16be_planar
pcm_s16le
pcm_s16le_planar
pcm_s24be
pcm_s24daud
pcm_s24le
pcm_s24le_planar
pcm_s32be
pcm_s32le
pcm_s32le_planar
pcm_s64be
pcm_s64le
pcm_s8
pcm_s8_planar
pcm_u16be
pcm_u16le
pcm_u24be
pcm_u24le
pcm_u32be
pcm_u32le
pcm_u8
pcm_vidc
pcm_zork
qcelp
qdm2
qdmc
ra_144
ra_288
ralf
roq_dpcm
s302m
sbc
sdx2_dpcm
shorten
sipr
smackaudio
smv
sol_dpcm
sonic
sonicls
speex
tak
truehd
truespeech
tta
twinvq
vmdaudio
vorbis
wavesynth
wavpack
westwood_snd1
wmalossless
wmapro
wmav1
wmav2
wmavoice
xan_dpcm
xma1
xma2
*/
